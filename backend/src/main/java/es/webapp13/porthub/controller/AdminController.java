package es.webapp13.porthub.controller;

import es.webapp13.porthub.model.Template;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.repository.TemplateRepository;
import es.webapp13.porthub.service.TemplateService;
import es.webapp13.porthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private TemplateService templateService;

    @GetMapping("/admin")
    public String adminLink(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/admin/banned/users")
    public String adminBannedUsersLink() {
        return "admin-banned-users";
    }

    @GetMapping("/admin/app/graphics")
    public String adminAppGraphicsLink(Model model) {
        long totalUsers = userService.getCountAll();
        model.addAttribute("totalUsers", totalUsers);
        return "admin-app-graphics";
    }

    @GetMapping("/admin/templates/list")
    public String adminTemplatesListLink() {
        return "admin-templates-list";
    }

    @PostMapping("/admin/templates/list/add/new")
    public String addNewTemplate(Model model, Template template) {
        if (template.getPrice() == 0){
            template.setFree(true);
        }
        else{
            template.setFree(false);
        }
        templateService.createTemplate(template);
        return "admin-templates-list";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(Model model, @PathVariable String id){
        User user = userService.findUser(id);
        userService.deleteUser(user);
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

}
