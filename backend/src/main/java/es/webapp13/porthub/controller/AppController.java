package es.webapp13.porthub.controller;

import es.webapp13.porthub.model.PortfolioItem;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.PortfolioItemService;
import es.webapp13.porthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.io.IOException;


@Controller
public class AppController {

    @Autowired
    private UserService userService;

    @Autowired
    private DefaultModelAttributes defaultModelAttributes;

    @Autowired
    private PortfolioItemService portfolioItemService;

    @GetMapping("/")
    public String indexLink(Model model) {
        return "index";
    }



    @GetMapping("/templates/free/index")
    public String templateFreeLink(Model model) {
        User user = userService.getActiveUser();
        model.addAttribute("user", user);
        return "templates/free/index";
    }

    @GetMapping("/templates/free/portfolioitem")
    public String templateFreePortfolioItemLink(Model model) {
        return "templates/free/portfolio-item";
    }

    @GetMapping("/templates/premium/index")
    public String templatePremiumLink(Model model) {
        User user = userService.getActiveUser();
        model.addAttribute("user", user);
        return "templates/premium/index";
    }

    @GetMapping("/templates/premium/portfolioitem")
    public String templatePremiumPortfolioItemLink(Model model) {
        return "templates/premium/portfolio-item";
    }

    @GetMapping("/shop")
    public String shopLink(Model model) {
        model.addAttribute("active_shop", true);
        model.addAttribute("user", userService.getActiveUser());
        return "shop";
    }

    @GetMapping("/purchase-confirmation")
    public String purchaseConfirmationLink(Model model){
        return "purchase-confirmation";
    }

    @GetMapping("/login")
    public String loginLink(Model model) {
        return "login";
    }

    @GetMapping("/reset-password")
    public String forgottenPasswordLink(Model model) {
        return "reset-password";
    }

    @GetMapping("/signup")
    public String signupLink(Model model) {
        return "signup";
    }

    @PostMapping("/signup-confirmation")
    public String signupConfirmationLink(Model model, User user) throws IOException {
        userService.createUser(user);
        userService.setActiveUser(user);
        defaultModelAttributes.setLogued(true);
        model.addAttribute("logued", true);
        return "signup-confirmation";
    }

    @GetMapping("/logout-confirmation")
    public String logoutConfirmationLink(Model model){
        userService.setActiveUser(null);
        defaultModelAttributes.setLogued(false);
        model.addAttribute("logued", false);
        return "logout-confirmation";
    }

    @GetMapping("/admin")
    public String adminLink(Model model) {
        return "admin";
    }

    @GetMapping("/banned-users")
    public String adminBannedUsersLink(Model model) {
        return "admin-banned-users";
    }

    @GetMapping("/app-graphics")
    public String adminAppGraphicsLink(Model model) {
        return "admin-app-graphics";
    }

    @GetMapping("/templates-list")
    public String adminTemplatesListLink(Model model) {
        return "admin-templates-list";
    }

}
