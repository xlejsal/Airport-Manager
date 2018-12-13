package cz.muni.fi.pa165.airportmanager.service.controllers;

import cz.muni.fi.pa165.airportmanager.api.facades.AirplaneFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
