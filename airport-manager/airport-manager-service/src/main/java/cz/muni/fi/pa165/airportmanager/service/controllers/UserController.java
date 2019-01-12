package cz.muni.fi.pa165.airportmanager.service.controllers;

import cz.muni.fi.pa165.airportmanager.api.dto.UserDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.UserFacade;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users", userFacade.getAllUsers());
        return "user/list";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("user", userFacade.getUserById(id));
        return "user/view";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        UserDTO user = userFacade.getUserById(id);

        if(user.isAdmin()){
            redirectAttributes.addFlashAttribute("alert_danger", "User \"" + user.getLogin() + "\" cannot be deleted - it's an admin!");
            return "redirect:" + uriBuilder.path("/user/view/" + id + "").toUriString();
        }

        try {
            userFacade.deleteUser(id);
            redirectAttributes.addFlashAttribute("alert_success", "User \"" + user.getLogin() + "\" was deleted.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "User \"" + user.getLogin() + "\" cannot be deleted.");
        }
        return "redirect:" + uriBuilder.path("/user/list").toUriString();
    }
}
