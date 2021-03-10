package es.webapp13.porthub.controller;

import es.webapp13.porthub.model.PortfolioItem;
import es.webapp13.porthub.service.PortfolioItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConfigController {

    @Autowired
    private PortfolioItemService portfolioItemService;

    @GetMapping("/student-edit-account")
    public String studentEditAccountLink(Model model) {
        model.addAttribute("active_main", true);
        return "student-edit-account";
    }

    @GetMapping("/student-edit-account-notifications")
    public String studentEditAccountNotificationsLink(Model model) {
        model.addAttribute("active_notifications", true);
        model.addAttribute("portfolioItems", portfolioItemService.getPortfolioItems("id"));
        return "student-edit-account-notifications";
    }


    @PostMapping("/student-edit-account-notifications")
    public String studentEditAccountNotificationsForm(Model model, PortfolioItem portfolioItem) {
        model.addAttribute("active_notifications", true);
        portfolioItemService.addPortfolioItem("id", portfolioItem);
        model.addAttribute("portfolioItems", portfolioItemService.getPortfolioItems("id"));
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
}
