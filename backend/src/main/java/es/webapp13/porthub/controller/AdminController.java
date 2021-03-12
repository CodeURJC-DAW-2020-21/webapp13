package es.webapp13.porthub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminLink(Model model) {
        return "admin";
    }

    @GetMapping("/admin/banned/users")
    public String adminBannedUsersLink(Model model) {
        return "admin-banned-users";
    }

    @GetMapping("/admin/app/graphics")
    public String adminAppGraphicsLink(Model model) {
        return "admin-app-graphics";
    }

    @GetMapping("/admin/templates/list")
    public String adminTemplatesListLink(Model model) {
        return "admin-templates-list";
    }


}
