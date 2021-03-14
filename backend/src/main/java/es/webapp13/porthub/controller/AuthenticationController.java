package es.webapp13.porthub.controller;

import es.webapp13.porthub.model.PurchasedTemplate;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.ActiveTemplateService;
import es.webapp13.porthub.service.PurchasedTemplateService;
import es.webapp13.porthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class AuthenticationController {

    @Autowired
    private PurchasedTemplateService purchasedTemplateService;

    @Autowired
    private ActiveTemplateService activeTemplateService;

    @Autowired
    private UserService userService;

    @Autowired
    private DefaultModelAttributes defaultModelAttributes;

    @RequestMapping("/login")
    public String loginLink(Model model) {
        return "login";
    }

    @RequestMapping("/loginerror")
    public String loginerror() {
        return "login";
    }

    @GetMapping("/reset/password")
    public String forgottenPasswordLink(Model model) {
        return "reset-password";
    }

    @GetMapping("/signup")
    public String signupLink(Model model) {
        return "signup";
    }

    @PostMapping("/signup/confirmation")
    public String signupConfirmationLink(Model model, User user) throws IOException {
        userService.createUser(user);
        //userService.setActiveUser(user);
        //defaultModelAttributes.setLogued(true);
        activeTemplateService.init(user.getTemplates(), user.getActiveTemplate());
        purchasedTemplateService.init(user.getTemplates());
        model.addAttribute("logued", true);
        return "signup-confirmation";
    }

    @GetMapping("/logout/confirmation")
    public String logoutConfirmationLink(Model model) {
        userService.saveChanges(userService.getActiveUser());
        //userService.setActiveUser(null);
        //defaultModelAttributes.setLogued(false);
        model.addAttribute("logued", false);
        return "logout-confirmation";
    }
}
