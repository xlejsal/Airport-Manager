package cz.muni.fi.pa165.airportmanager.service.controllers;

import cz.muni.fi.pa165.airportmanager.api.dto.AirplaneDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.AirplaneFacade;
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
@RequestMapping("/airplane")
public class AirplaneControler {

    @Autowired
    AirplaneFacade airplaneFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("airplanes", airplaneFacade.getAllAirplanes());
        return "airplane/list";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("airplane", airplaneFacade.getAirplaneById(id));
        return "airplane/view";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        AirplaneDTO airplane = airplaneFacade.getAirplaneById(id);
        try {
            airplaneFacade.deleteAirplane(id);
            redirectAttributes.addFlashAttribute("alert_success", "Airplane \"" + airplane.getName() + "\" was deleted.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "Airplane \"" + airplane.getName() + "\" cannot be deleted.");
        }
        return "redirect:" + uriBuilder.path("/airplane/list").toUriString();
    }
}
