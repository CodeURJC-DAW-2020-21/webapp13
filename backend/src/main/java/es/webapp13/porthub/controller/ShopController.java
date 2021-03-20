package es.webapp13.porthub.controller;

import es.webapp13.porthub.model.PurchasedTemplate;
import es.webapp13.porthub.model.Template;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.ActiveTemplateService;
import es.webapp13.porthub.service.PurchasedTemplateService;
import es.webapp13.porthub.service.TemplateService;
import es.webapp13.porthub.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ShopController {

    private final ActiveTemplateService activeTemplateService;

    private final PurchasedTemplateService purchasedTemplateService;

    private final UserService userService;

    private final TemplateService templateService;

    public ShopController(ActiveTemplateService activeTemplateService, PurchasedTemplateService purchasedTemplateService, UserService userService, TemplateService templateService) {
        this.activeTemplateService = activeTemplateService;
        this.purchasedTemplateService = purchasedTemplateService;
        this.userService = userService;
        this.templateService = templateService;
    }

    @GetMapping("/shop")
    public String shopLink(Model model, HttpServletRequest request) {
        model.addAttribute("active_shop", true);
        Principal principal = request.getUserPrincipal();
        if (principal==null){
            model.addAttribute("templates", templateService.findAll());
        }else{
            model.addAttribute("templates", purchasedTemplateService.getTemplateList());
        }
        return "shop";
    }

    @GetMapping("/purchase/confirmation")
    public String purchaseConfirmationLink(HttpServletRequest request, @RequestParam long id) {
        purchasedTemplateService.purchase(id);
        Template template = templateService.findFirstById(id);
        Principal principal = request.getUserPrincipal();
        User user = userService.findUser(principal.getName());
        user.getTemplates().add(template);
        activeTemplateService.addTemplate(template);
        return "purchase-confirmation";
    }

    @GetMapping("/recommendTemplate")
    public String recommendTemplate(Model model, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        User user = userService.findUser(principal.getName());
        List<PurchasedTemplate> purchasedTemplates = new LinkedList<>();
        purchasedTemplates.add(userService.getPopularTemplate(user.getid()));
        model.addAttribute("templates", purchasedTemplates);
        return "shop";
    }

}
