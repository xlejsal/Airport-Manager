package cz.muni.fi.pa165.airportmanager.service.controllers;

import cz.muni.fi.pa165.airportmanager.api.dto.AirplaneDTO;
import cz.muni.fi.pa165.airportmanager.api.dto.DestinationDTO;
import cz.muni.fi.pa165.airportmanager.api.dto.FlightDTO;
import cz.muni.fi.pa165.airportmanager.api.dto.StewardDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.AirplaneFacade;
import cz.muni.fi.pa165.airportmanager.api.facades.DestinationFacade;
import cz.muni.fi.pa165.airportmanager.api.facades.FlightFacade;
import cz.muni.fi.pa165.airportmanager.api.facades.StewardFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

/**
 * @author kotrc
 * Created on 14.12.2018
 */
@Controller
@RequestMapping("/flight")
public class FlightController {

    private final static Logger log = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    FlightFacade flightFacade;
    @Autowired
    DestinationFacade destinationFacade;
    @Autowired
    StewardFacade stewardFacade;
    @Autowired
    AirplaneFacade airplaneFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<FlightDTO> flightList = flightFacade.getAllFlights();
        flightList.sort(Comparator.comparing(FlightDTO::getDepartureTime));
        model.addAttribute("flights", flightList);
        return "flight/list";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("flight", flightFacade.getFlightById(id));
        return "flight/view";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        FlightDTO flight = flightFacade.getFlightById(id);
        try {
            flightFacade.deleteFlight(id);
            redirectAttributes.addFlashAttribute("alert_success", "Flight \"" + flight.getFlightNumber() + "\" was deleted.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "Flight \"" + flight.getFlightNumber() + "\" cannot be deleted.");
        }
        return "redirect:" + uriBuilder.path("/flight/list").toUriString();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newFlight(Model model) {
        log.debug("new()");
        model.addAttribute("flightDto", new FlightDTO());
        return "flight/new";
    }

    /**
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof AirplaneDTO) {
            binder.addValidators(new AirplaneDTOValidator(airplaneFacade));
        }
    }**/

    @ModelAttribute("origin")
    public List<DestinationDTO> origin() {
        log.debug("origin()");
        log.debug(destinationFacade.getAllDestinations().toString());
        return destinationFacade.getAllDestinations();
    }

    @ModelAttribute("destination")
    public List<DestinationDTO> destination() {
        log.debug("destination()");
        return destinationFacade.getAllDestinations();
    }

    @ModelAttribute("airplane")
    public List<AirplaneDTO> airplane() {
        log.debug("airplane()");
        return airplaneFacade.getAllAirplanes();
    }

    @ModelAttribute("stewards")
    public List<StewardDTO> stewards() {
        log.debug("stewards()");
        return stewardFacade.getAllStewards();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("flightDto") FlightDTO flight, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(flightDto={})", flight);
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "flight/new";
        }
        //create product
        FlightDTO newFlight = flightFacade.createFlight(flight);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Flight " + newFlight.getFlightNumber() + " was created");
        return "redirect:" + uriBuilder.path("/flight/list").toUriString();
    }
}
