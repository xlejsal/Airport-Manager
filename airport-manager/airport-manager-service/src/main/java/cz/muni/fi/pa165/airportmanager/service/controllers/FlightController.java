package cz.muni.fi.pa165.airportmanager.service.controllers;

import cz.muni.fi.pa165.airportmanager.api.dto.AirplaneDTO;
import cz.muni.fi.pa165.airportmanager.api.dto.DestinationDTO;
import cz.muni.fi.pa165.airportmanager.api.dto.FlightDTO;
import cz.muni.fi.pa165.airportmanager.api.dto.StewardDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.AirplaneFacade;
import cz.muni.fi.pa165.airportmanager.api.facades.DestinationFacade;
import cz.muni.fi.pa165.airportmanager.api.facades.FlightFacade;
import cz.muni.fi.pa165.airportmanager.api.facades.StewardFacade;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.models.FlightPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newFlight(Model model) {
        log.debug("new()");
        model.addAttribute("flightDto", new FlightDTO());
        return "flight/new";
    }

    @ModelAttribute("origin")
    public List<DestinationDTO> origin() {
        log.debug("origin()");
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
    public String create(@Valid @ModelAttribute("flightDto") FlightDTO flightDto, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(flightDto={})", flightDto);
        //log.debug(flightDto.getFlightNumber());
        //log.debug(flightDto.getAirplane().getName());
        //log.debug(flightDto.getDepartureTime().toString());
        log.debug(flightDto.getOrigin().getAirportCode());
        log.debug(flightDto.getArrivalTime().toString());
        log.debug(flightDto.getDestination().getAirportCode());
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
        FlightDTO flight = flightFacade.createFlight(flightDto);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Flight " + flight.getFlightNumber() + " was created");
        return "redirect:" + uriBuilder.path("/flight/list").toUriString();
    }
}
