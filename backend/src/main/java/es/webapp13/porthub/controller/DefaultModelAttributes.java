package es.webapp13.porthub.controller;

import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@ControllerAdvice
public class DefaultModelAttributes {

    @Autowired
    private UserService userService;

    //ModelAttribute can be used in every html without been named in the specific model's controller
    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {


        Principal principal = request.getUserPrincipal();
        //Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        if(principal != null) {
            String name = principal.getName();

            User user = userService.findUser(name);
            model.addAttribute("logued", true);
            model.addAttribute("username", principal.getName());
            model.addAttribute("user", user);
            model.addAttribute("admin", request.isUserInRole("ADMIN"));

            /*
            String username = ((UserDetails)principal).getUsername();
            User user = userService.findUser(username);
            model.addAttribute("logued", true);
            //model.addAttribute("username", principal.getName());
            //model.addAttribute("username", user.getName());
            model.addAttribute("user", user);

             */
        } else {
            model.addAttribute("logued", false);
        }
    }


    /*
    private boolean logued;

    public DefaultModelAttributes() {
        this.logued = false;
    }

    @ModelAttribute("logued")
    public Boolean userLogued() {
        return logued;
    }

    public void setLogued(boolean logued) {
        this.logued = logued;
    }
*/


}
