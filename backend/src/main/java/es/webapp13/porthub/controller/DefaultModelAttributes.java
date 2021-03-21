package es.webapp13.porthub.controller;

import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@ControllerAdvice
public class DefaultModelAttributes {

    private final UserService userService;

    public DefaultModelAttributes(UserService userService) {
        this.userService = userService;
    }

    //ModelAttribute can be used in every html without been named in the specific model's controller
    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if (principal != null) {
            String name = principal.getName();
            User user = userService.findUser(name);
            model.addAttribute("logued", true);
            model.addAttribute("username", principal.getName());
            model.addAttribute("user", user);
            model.addAttribute("admin", request.isUserInRole("ADMIN"));

        } else {
            model.addAttribute("logued", false);
        }
    }

}
