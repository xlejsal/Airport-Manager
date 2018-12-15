package cz.muni.fi.pa165.airportmanager.service.controllers;

import cz.muni.fi.pa165.airportmanager.api.dto.FlightDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.FlightFacade;
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("flights", flightFacade.getAllFlights());
        return "flight/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newFlight(Model model) {
        log.debug("new()");
        model.addAttribute("flightCreate", new FlightDTO());
        return "flight/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("flightCreate") FlightDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(formBean={})", formBean);
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
        FlightDTO flight = flightFacade.createFlight(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Flight " + flight + " was created");
        return "redirect:" + uriBuilder.path("/flight/list").toUriString();
    }
}
