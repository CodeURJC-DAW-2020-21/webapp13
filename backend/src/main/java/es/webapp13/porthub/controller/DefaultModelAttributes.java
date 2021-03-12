package es.webapp13.porthub.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@ControllerAdvice
public class DefaultModelAttributes {

    /*
    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if(principal != null) {

            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));

        } else {
            model.addAttribute("logged", false);
        }
    }
     */

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



}
