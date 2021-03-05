package es.webapp13.porthub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/")
    public String indexLink(Model model) {
        return "index";
    }

    @GetMapping("/search")
    public String searchLink(Model model) {
        model.addAttribute("active_search", true);
        return "search";
    }

    @GetMapping("/templates/premium/index")
    public String templateLink(Model model) {
        return "templates/premium/index";
    }

    @GetMapping("/templates/premium/portfolioitem")
    public String templatePortfolioItemLink(Model model) {
        return "templates/premium/portfolio-item";
    }

    @GetMapping("/shop")
    public String shopLink(Model model) {
        model.addAttribute("active_shop", true);
        return "shop";
    }

    @GetMapping("/student-edit-account")
    public String studentEditAccountLink(Model model) {
        model.addAttribute("active_main", true);
        return "student-edit-account";
    }

    @GetMapping("/student-edit-account-notifications")
    public String studentEditAccountNotificationsLink(Model model) {
        model.addAttribute("active_notifications", true);
        return "student-edit-account-notifications";
    }

    @GetMapping("/student-edit-account-password")
    public String studentEditAccountPasswordLink(Model model) {
        model.addAttribute("active_password", true);
        return "student-edit-account-password";
    }

    @GetMapping("/student-edit-account-profile")
    public String studentEditAccountProfileLink(Model model) {
        model.addAttribute("active_profile", true);
        return "student-edit-account-profile";
    }

    @GetMapping("/login")
    public String loginLink(Model model) {
        return "login";
    }

    @GetMapping("/signup")
    public String signupLink(Model model) {
        return "signup";
    }


}
