package es.webapp13.porthub.controller;

import es.webapp13.porthub.model.Template;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortfolioController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String profileLink(Model model) {
        User user = userService.getActiveUser();
        if (user == null) {
            return templateFreeLink(model);
        } else {
            model.addAttribute("user", user);
            Template template = userService.getActiveUser().getActiveTemplate();
            String htmlPath = template.getHtmlPath();
            return htmlPath;
        }
    }

    @GetMapping("/templates/free/index")
    public String templateFreeLink(Model model) {
        User user = userService.getActiveUser();
        model.addAttribute("user", user);
        return "templates/free/index";
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

    @GetMapping("/templates/free/portfolioitem")
    public String templateFreePortfolioItemLink(Model model) {
        return "templates/free/portfolio-item";
    }

}
