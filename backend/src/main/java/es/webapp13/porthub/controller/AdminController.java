package es.webapp13.porthub.controller;

import es.webapp13.porthub.model.Template;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.PurchasedTemplateService;
import es.webapp13.porthub.service.TemplateService;
import es.webapp13.porthub.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
public class AdminController {

    private final PurchasedTemplateService purchasedTemplateService;

    private final UserService userService;

    private final TemplateService templateService;

    public AdminController(UserService userService, TemplateService templateService, PurchasedTemplateService purchasedTemplateService) {
        this.userService = userService;
        this.templateService = templateService;
        this.purchasedTemplateService = purchasedTemplateService;
    }

    @RequestMapping("/admin")
    public String adminLink(Model model) {
        Collection<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin";
    }


    @GetMapping("/admin/app/graphics")
    public String adminAppGraphicsLink(Model model) {
        long totalUsers = userService.countAll();
        Collection<Integer> usersPerMonth = userService.countAllByCreationDateMonth();
        model.addAttribute("usersPerMonth", usersPerMonth);
        return "admin-app-graphics";
    }

    @GetMapping("/admin/templates/list")
    public String adminTemplatesListLink(Model model) {

        List<Template> templates = templateService.findAll();
        model.addAttribute("templates", templates);
        return "admin-templates-list";
    }

    @PostMapping("/admin/templates/list/add/new")
    public String addNewTemplate(Model model, Template template) {
        if (template.getPrice() == 0) {
            template.setFree(true);
            template.setHtmlPath(template.getHtmlPath() + "free/index");
        } else {
            template.setFree(false);
            template.setHtmlPath(template.getHtmlPath() + "premium/index");
        }
        templateService.create(template);
        List<Template> templates = templateService.findAll();
        model.addAttribute("templates", templates);
        purchasedTemplateService.add(template.getId());
        return "admin-templates-list";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(Model model, @PathVariable String id) {
        User user = userService.findById(id).orElseThrow();
        userService.delete(user);
        Collection<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin";
    }

}
