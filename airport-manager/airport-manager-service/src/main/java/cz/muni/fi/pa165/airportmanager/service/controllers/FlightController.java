package cz.muni.fi.pa165.airportmanager.service.controllers;

import cz.muni.fi.pa165.airportmanager.api.dto.*;
import cz.muni.fi.pa165.airportmanager.api.facades.AirplaneFacade;
import cz.muni.fi.pa165.airportmanager.api.facades.DestinationFacade;
import cz.muni.fi.pa165.airportmanager.api.facades.FlightFacade;
import cz.muni.fi.pa165.airportmanager.api.facades.StewardFacade;
import cz.muni.fi.pa165.airportmanager.service.services.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.service.validators.FlightCreateDTOValidator;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    BeanMappingService beanMapper;

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

    @RequestMapping(value = "/view/{id}/stewards", method = RequestMethod.GET)
    public String stewards(@PathVariable long id, Model model) {
        model.addAttribute("stewards", flightFacade.getFlightById(id).getStewards());
        model.addAttribute("flight", flightFacade.getFlightById(id));
        return "flight/stewards";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        FlightDTO flight = flightFacade.getFlightById(id);
        log.debug("delete()");
        try {
            flightFacade.deleteFlight(id);
            redirectAttributes.addFlashAttribute("alert_success", "Flight \"" + flight.getFlightNumber() + "\" was deleted.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "Flight \"" + flight.getFlightNumber() + "\" cannot be deleted.");
        }
        return "redirect:" + uriBuilder.path("/flight/list").toUriString();
    }

    @GetMapping("/{flightNumber}/remove/steward/{stewardId}")
    public String removeSteward(@PathVariable String flightNumber, @PathVariable long stewardId, Model model) {
        FlightDTO flight = flightFacade.getFlightByFlightNumber(flightNumber);
        StewardDTO steward = stewardFacade.getStewardById(stewardId);
        StewardWithoutFlightsDTO stewardNoFlights = beanMapper.mapTo(steward, StewardWithoutFlightsDTO.class);
        flight.getStewards().remove(stewardNoFlights);
        flightFacade.updateFlight(flight);

        if (model.containsAttribute("availableStewards")) {
            List<StewardDTO> availableStewards = stewardFacade.getAvailableStewardsFromTo(flight.getDepartureTime(), flight.getArrivalTime());
            if (!availableStewards.contains(steward)) {
                availableStewards.add(steward);
            }
            model.addAttribute("availableStewards", availableStewards);
        }
        model.addAttribute("flight", flight);
        model.addAttribute("stewards", flight.getStewards());
        return "flight/stewards";
    }

    @GetMapping("/{flightNumber}/add/steward/{stewardId}")
    public String addSteward(@PathVariable String flightNumber, @PathVariable long stewardId, Model model) {
        FlightDTO flight = flightFacade.getFlightByFlightNumber(flightNumber);
        StewardDTO steward = stewardFacade.getStewardById(stewardId);
        StewardWithoutFlightsDTO stewardNoFlights = beanMapper.mapTo(steward, StewardWithoutFlightsDTO.class);
        flight.getStewards().add(stewardNoFlights);
        flightFacade.updateFlight(flight);

        if (model.containsAttribute("availableStewards")) {
            List<StewardDTO> availableStewards = stewardFacade.getAvailableStewardsFromTo(flight.getDepartureTime(), flight.getArrivalTime());
            availableStewards.remove(steward);
            model.addAttribute("availableStewards", availableStewards);
        }
        model.addAttribute("flight", flight);
        model.addAttribute("stewards", flight.getStewards());
        return "flight/stewards";
    }

    @GetMapping("/{flightNumber}/stewards/toggle")
    public String toggleAvailableStewardsTable(@PathVariable String flightNumber, Model model) {
        FlightDTO flight = flightFacade.getFlightByFlightNumber(flightNumber);
        model.addAttribute("availableStewards", stewardFacade.getAvailableStewardsFromTo(flight.getDepartureTime(), flight.getArrivalTime()));
        model.addAttribute("flight", flight);
        model.addAttribute("stewards", flight.getStewards());
        return "flight/stewards";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof FlightCreateDTO) {
            binder.addValidators(new FlightCreateDTOValidator(flightFacade));
        }
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newFlight(Model model) {
        log.debug("new()");
        model.addAttribute("flightDto", new FlightCreateDTO());
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
    public String create(@Valid @ModelAttribute("flightDto") FlightCreateDTO flight, BindingResult bindingResult,
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

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("flight") FlightDTO flight, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "flight/view";
        }

        FlightDTO dbFlight = flightFacade.getFlightById(flight.getId());
        dbFlight.setFlightNumber(flight.getFlightNumber());
        dbFlight.setArrivalTime(flight.getArrivalTime());
        dbFlight.setDepartureTime(flight.getDepartureTime());
        dbFlight.setOrigin(destinationFacade.getDestinationById(flight.getOrigin().getId()));
        dbFlight.setDestination(destinationFacade.getDestinationById(flight.getDestination().getId()));
        dbFlight.setAirplane(airplaneFacade.getAirplaneById(flight.getAirplane().getId()));

        flightFacade.updateFlight(dbFlight);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Flight " + flight.getFlightNumber() + " was edited");
        return "redirect:" + uriBuilder.path("/flight/view/{id}").buildAndExpand(flight.getId()).encode().toUriString();
    }
}
