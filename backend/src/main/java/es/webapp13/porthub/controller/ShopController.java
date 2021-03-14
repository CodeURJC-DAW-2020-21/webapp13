package es.webapp13.porthub.controller;

import es.webapp13.porthub.model.Template;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.repository.TemplateRepository;
import es.webapp13.porthub.service.ActiveTemplateService;
import es.webapp13.porthub.service.PurchasedTemplateService;
import es.webapp13.porthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;

@Controller
public class ShopController {

    @Autowired
    private ActiveTemplateService activeTemplateService;

    @Autowired
    private PurchasedTemplateService purchasedTemplateService;

    @Autowired
    private UserService userService;

    @Autowired
    private TemplateRepository templateRepository;

    @GetMapping("/shop")
    public String shopLink(Model model) {
        model.addAttribute("active_shop", true);
        User user = userService.getActiveUser();
        if (user==null){
            model.addAttribute("templates", templateRepository.findAll());
            model.addAttribute("user", false);
        }else{
            model.addAttribute("templates", purchasedTemplateService.getTemplateList());
            model.addAttribute("user", true);
        }
        return "shop";
    }

    @GetMapping("/purchase/confirmation")
    public String purchaseConfirmationLink(Model model, @RequestParam long id) {
        purchasedTemplateService.purchase(id);
        Template template = templateRepository.findFirstById(id);
        User user = userService.getActiveUser();
        user.getTemplates().add(template);
        //userService.saveChanges(user);
        activeTemplateService.addTemplate(template);
        return "purchase-confirmation";
    }
}
