package cz.muni.fi.pa165.airportmanager.service.controllers;

import cz.muni.fi.pa165.airportmanager.api.dto.DestinationDTO;
import cz.muni.fi.pa165.airportmanager.api.dto.DestinationUpdateDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.DestinationFacade;
import cz.muni.fi.pa165.airportmanager.service.services.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.service.validators.DestinationDTOValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedExceptionUtils;
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

@Controller
@RequestMapping("/destination")
public class DestinationController {

    private final DestinationFacade facade;
    private final static Logger log = LoggerFactory.getLogger(DestinationController.class);

    @Autowired
    BeanMappingService beanMapper;

    @Autowired
    public DestinationController(DestinationFacade facade) {
        this.facade = facade;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("destinations", facade.getAllDestinations());
        return "destination/list";
    }

    @GetMapping("/new")
    public String newDestination(Model model) {
        model.addAttribute("destinationCreate", new DestinationDTO());
        return "destination/new";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof DestinationDTO) {
            binder.addValidators(new DestinationDTOValidator(facade));
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("destinationCreate") DestinationDTO destination, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(destinationCreate={})", destination);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "destination/new";
        }
        //create destination
        Long id = facade.createDestination(destination).getId();
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Destination " + destination.getAirportCode() + " was created");
        return "redirect:" + uriBuilder.path("/destination/list").toUriString();
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable long id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("destinationUpdate", facade.getDestinationById(id));
        return "destination/view";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("destinationUpdate") DestinationUpdateDTO destination, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("update(destinationUpdate={})", destination.toString());
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "destination/view";
        }
        //create destination
        DestinationDTO newDestination = facade.updateDestination(beanMapper.mapTo(destination, DestinationDTO.class));
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Destination " + newDestination.getAirportCode() + " was updated");
        return "redirect:" + uriBuilder.path("/destination/list").toUriString();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        DestinationDTO destination = facade.getDestinationById(id);
        log.debug("delete({})", id);
        try {
            facade.deleteDestination(id);
            redirectAttributes.addFlashAttribute("alert_success", "Destination \"" + destination.getAirportCode() + "\" was deleted.");
        } catch (Exception ex) {
            log.error("destination "+id+" cannot be deleted (it is included in an flight)");
            log.error(NestedExceptionUtils.getMostSpecificCause(ex).getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", "Destination \"" + destination.getAirportCode() + "\" cannot be deleted.");
        }
        return "redirect:" + uriBuilder.path("/destination/list").toUriString();
    }
}
