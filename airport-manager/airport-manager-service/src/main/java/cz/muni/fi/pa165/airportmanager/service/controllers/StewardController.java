package cz.muni.fi.pa165.airportmanager.service.controllers;

import cz.muni.fi.pa165.airportmanager.api.dto.StewardDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.StewardFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author: Stepan Benes
 */

@Controller
@RequestMapping("/steward")
public class StewardController {

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
}
