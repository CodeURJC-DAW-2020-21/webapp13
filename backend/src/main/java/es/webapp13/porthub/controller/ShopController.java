package es.webapp13.porthub.controller;

import es.webapp13.porthub.repository.TemplateRepository;
import es.webapp13.porthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {

    @Autowired
    private UserService userService;

    @Autowired
    private TemplateRepository templateRepository;

    @GetMapping("/shop")
    public String shopLink(Model model) {
        model.addAttribute("active_shop", true);
        model.addAttribute("user", userService.getActiveUser());
        model.addAttribute("templates", templateRepository.findAll());
        return "shop";
    }

    @GetMapping("/purchase/confirmation")
    public String purchaseConfirmationLink(Model model) {
        return "purchase-confirmation";
    }
}
