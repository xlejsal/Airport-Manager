package cz.muni.fi.pa165.airportmanager.service.controllers;

import cz.muni.fi.pa165.airportmanager.api.dto.StewardDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.StewardFacade;
import cz.muni.fi.pa165.airportmanager.persistence.repositories.enums.Gender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 *
 * @author: Stepan Benes
 */

@Controller
@RequestMapping("/steward")
public class StewardController {

    private final static Logger log = LoggerFactory.getLogger(AirplaneController.class);

    @Autowired
    StewardFacade stewardFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("stewards", stewardFacade.getAllStewards());
        return "steward/list";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("steward", stewardFacade.getStewardById(id));
        return "steward/view";
    }

    @RequestMapping(value = "/view/{id}/flights", method = RequestMethod.GET)
    public String flights(@PathVariable long id, Model model) {
        model.addAttribute("flights", stewardFacade.getFlightsOfSteward(id));
        model.addAttribute("steward", stewardFacade.getStewardById(id));
        return "steward/flights";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        StewardDTO steward = stewardFacade.getStewardById(id);
        try {
            stewardFacade.deleteSteward(id);
            redirectAttributes.addFlashAttribute("alert_success", "Steward \"" + steward.getName() + " " + steward.getSurname() + "\" was deleted.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "Steward \"" + steward.getName() + " " + steward.getSurname() + "\" cannot be deleted.");
        }
        return "redirect:" + uriBuilder.path("/steward/list").toUriString();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("stewardCreate") StewardDTO steward, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "steward/new";
        }
        StewardDTO newSteward = stewardFacade.createSteward(steward);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Steward " + newSteward.getName()+ " " + newSteward.getSurname() + " was created");
        return "redirect:" + uriBuilder.path("/steward/list").toUriString();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("steward") StewardDTO steward, BindingResult bindingResult, @PathVariable long id,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "steward/edit";
        }
        StewardDTO newSteward = stewardFacade.createSteward(steward);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Steward " + newSteward.getName()+ " " + newSteward.getSurname() + " was edited");
        return "redirect:" + uriBuilder.path("/steward/list").toUriString();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAirplane(Model model) {
        model.addAttribute("stewardCreate", new StewardDTO());
        return "steward/new";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("steward", stewardFacade.getStewardById(id));
        return "steward/edit";
    }

    @ModelAttribute("genders")
    public Gender[] colors() {
        return Gender.values();
    }

}
