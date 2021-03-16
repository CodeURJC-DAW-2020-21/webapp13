package es.webapp13.porthub.controller;

import es.webapp13.porthub.model.Template;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.PortfolioItemService;
import es.webapp13.porthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class TemplateController {

    @Autowired
    private UserService userService;

    @Autowired
    private PortfolioItemService portfolioItemService;

    @GetMapping("/profile")
    public String profileLink(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userService.findUser(principal.getName());
        model.addAttribute("portfolioItems", user.getPortfolioItems());
        Template template = user.getActiveTemplate();
        String htmlPath = template.getHtmlPath();
        return htmlPath;
    }

    @GetMapping("/templates/free/index")
    public String templateFreeLink(Model model) {
        return "templates/free/index";
    }


    @GetMapping("/templates/premium/index")
    public String templatePremiumLink(Model model) {
        return "templates/premium/index";
    }

    @GetMapping("/templates/premium/portfolioitem/{id}")
    public String templatePremiumPortfolioItemLink(Model model,@PathVariable long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userService.findUser(principal.getName());
        model.addAttribute("portfolioItem", portfolioItemService.getPortfolioItem(user.getid(), id));
        return "templates/premium/portfolio-item";
    }


    @GetMapping("/templates/free/portfolioitem/{id}")
    public String templateFreePortfolioItemLink(Model model, @PathVariable long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userService.findUser(principal.getName());
        model.addAttribute("portfolioItem", portfolioItemService.getPortfolioItem(user.getid(), id));
        return "templates/free/portfolio-item";
    }



}
