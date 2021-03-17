package es.webapp13.porthub.controller;

import es.webapp13.porthub.model.User;
import es.webapp13.porthub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin")
    public String adminLink(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users",users);
        return "admin";
    }

    @GetMapping("/admin/banned/users")
    public String adminBannedUsersLink(Model model) {
        return "admin-banned-users";
    }

    @GetMapping("/admin/app/graphics")
    public String adminAppGraphicsLink(Model model) {
        long totalUsers = userRepository.count();
        model.addAttribute("totalUsers", totalUsers);
        return "admin-app-graphics";
    }

    @GetMapping("/admin/templates/list")
    public String adminTemplatesListLink(Model model) {
        return "admin-templates-list";
    }


}
