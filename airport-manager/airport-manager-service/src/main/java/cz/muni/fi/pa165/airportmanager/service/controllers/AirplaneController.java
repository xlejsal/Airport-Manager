package cz.muni.fi.pa165.airportmanager.service.controllers;

import cz.muni.fi.pa165.airportmanager.api.dto.AirplaneDTO;
import cz.muni.fi.pa165.airportmanager.api.dto.AirplaneUpdateDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.AirplaneFacade;
import cz.muni.fi.pa165.airportmanager.service.services.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.service.validators.AirplaneDTOValidator;
import cz.muni.fi.pa165.airportmanager.service.validators.AirplaneUpdateDTOValidator;
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

/**
 *
 * @author: Stepan Benes
 */

@Controller
@RequestMapping("/airplane")
public class AirplaneController {

    private final static Logger log = LoggerFactory.getLogger(AirplaneController.class);

    @Autowired
    AirplaneFacade airplaneFacade;

    @Autowired
    BeanMappingService beanMapper;

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


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof AirplaneDTO) {
            binder.addValidators(new AirplaneDTOValidator(airplaneFacade));
        }
        if (binder.getTarget() instanceof AirplaneUpdateDTO) {
            binder.addValidators(new AirplaneUpdateDTOValidator(airplaneFacade));
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("airplaneCreate") AirplaneDTO plane, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "airplane/new";
        }
        AirplaneDTO newAirplane = airplaneFacade.createAirplane(plane);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Airplane " + newAirplane.getName()+ " was created");
        return "redirect:" + uriBuilder.path("/airplane/list").toUriString();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("airplane") AirplaneUpdateDTO plane, BindingResult bindingResult, @PathVariable long id,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "airplane/edit";
        }
        AirplaneDTO newAirplane = airplaneFacade.updateAirplane(beanMapper.mapTo(plane, AirplaneDTO.class));
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Airplane " + newAirplane.getName()+ " was edited");
        return "redirect:" + uriBuilder.path("/airplane/list").toUriString();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAirplane(Model model) {
        model.addAttribute("airplaneCreate", new AirplaneDTO());
        return "airplane/new";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("airplane", airplaneFacade.getAirplaneById(id));
        return "airplane/edit";
    }
}
