package es.webapp13.porthub.controller;


import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.ActiveTemplateService;
import es.webapp13.porthub.service.PurchasedTemplateService;
import es.webapp13.porthub.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.rmi.ServerException;
import java.security.Principal;

@Controller
public class AuthenticationController {

    private final PurchasedTemplateService purchasedTemplateService;

    private final ActiveTemplateService activeTemplateService;

    private final UserService userService;

    public AuthenticationController(PurchasedTemplateService purchasedTemplateService, ActiveTemplateService activeTemplateService, UserService userService) {
        this.purchasedTemplateService = purchasedTemplateService;
        this.activeTemplateService = activeTemplateService;
        this.userService = userService;
    }


    @RequestMapping("/login")
    public String loginLink() {
        return "login";
    }

    @RequestMapping("/loginerror")
    public String loginerror() {
        return "login-error";
    }

    @GetMapping("/signup")
    public String signupLink() {
        return "signup";
    }

    @PostMapping("/signup/confirmation")
    public String signupConfirmationLink(Model model, User user, HttpServletRequest request) throws IOException {
        String password = user.getPassword();
        userService.createUser(user);
        activeTemplateService.init(user.getTemplates(), user.getActiveTemplate());
        purchasedTemplateService.init(user.getTemplates());
        try {
            request.login(user.getId(), password);
            model.addAttribute("loginProcess", true);
        } catch (ServletException e) {
            throw new ServerException("ServletException at signup");
        }
        return "signup-confirmation";
    }

    @GetMapping("/logout/confirmation")
    public String logoutConfirmationLink(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userService.findUser(principal.getName());
        userService.save(user);
        model.addAttribute("logued", false);
        return "logout-confirmation";
    }


}
