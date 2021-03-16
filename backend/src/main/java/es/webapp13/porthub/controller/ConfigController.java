package es.webapp13.porthub.controller;

import es.webapp13.porthub.repository.UserRepository;
import es.webapp13.porthub.model.PortfolioItem;
import es.webapp13.porthub.model.Template;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.ActiveTemplateService;
import es.webapp13.porthub.service.PortfolioItemService;
import es.webapp13.porthub.service.TemplateService;
import es.webapp13.porthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Optional;

@Controller
public class ConfigController {

    @Autowired
    private ActiveTemplateService activeTemplateService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private PortfolioItemService portfolioItemService;

    @GetMapping("/settings/edit/account")
    public String studentEditAccountLink(Model model,HttpServletRequest request) {
        model.addAttribute("active_main", true);
        Principal principal = request.getUserPrincipal();
        User user = userService.findUser(principal.getName());
        model.addAttribute("user",user);
        return "settings-edit-account";
    }

    @GetMapping("/settings/edit/account/portfolioitems")
    public String studentEditAccountNotificationsLink(Model model,HttpServletRequest request) {
        model.addAttribute("active_notifications", true);
        Principal principal = request.getUserPrincipal();
        User user = userService.findUser(principal.getName());
        model.addAttribute("portfolioItems", portfolioItemService.getPortfolioItems(user.getid()));
        return "settings-edit-account-portfolioitems";
    }


    @PostMapping("/settings/edit/account/portfolioitems")
    public String studentEditAccountNotificationsForm(Model model,HttpServletRequest request, PortfolioItem portfolioItem) {
        model.addAttribute("active_notifications", true);

        Principal principal = request.getUserPrincipal();
        User user = userService.findUser(principal.getName());

        portfolioItemService.addPortfolioItem(user.getid(), portfolioItem);
        model.addAttribute("portfolioItems", portfolioItemService.getPortfolioItems(user.getid()));
        return "settings-edit-account-portfolioitems";
    }

    //Cambiar esto
    @GetMapping("/settings/edit/account/deleted/portfolio-item")
    public String portfolioItemDeleteLink() {
        portfolioItemService.deletePortfolioItem("id", 3);
        return "settings-edit-account-portfolioitems";
    }

    @GetMapping("/settings/edit/account/edit/portfolioitem/{userId}/{id}")
    public String portfolioItemEditLink(Model model,@PathVariable long id, @PathVariable String userId) {
        model.addAttribute("portfolioItem",portfolioItemService.getPortfolioItem(userId,id));
        return "settings-edit-account-edit-portfolioitem";
    }

    @PostMapping("/settings/edit/account/edit/portfolioitem/{userId}/{id}")
    public String portfolioItemEditForm(Model model,@PathVariable long id, @PathVariable String userId,PortfolioItem newPortfolioItem) {
        portfolioItemService.updatePortfolioItem(newPortfolioItem,id);
        return "portfolioitem-update-confirmation";
    }

    @GetMapping("/settings/edit/account/password")
    public String studentEditAccountPasswordLink(Model model) {
        model.addAttribute("active_password", true);
        return "settings-edit-account-password";
    }

    @GetMapping("/settings/edit/account/profile")
    public String studentEditAccountProfileLink(Model model) {
        model.addAttribute("active_profile", true);
        return "settings-edit-account-profile";
    }

    @GetMapping("/settings/edit/account/my-templates")
    public String userTemplatesLink(Model model){
        model.addAttribute("templates", activeTemplateService.getActiveTemplateList());
        return "settings-edit-account-mytemplates";
    }

    @GetMapping("/set/active/template")
    public String activeTemplateLink(Model model, HttpServletRequest request, @RequestParam long id){
        Principal principal = request.getUserPrincipal();
        User user = userService.findUser(principal.getName());
        long oldId = user.getActiveTemplate().getId();
        activeTemplateService.changeActiveTemplate(oldId, id);
        Template activeTemplate = templateService.findFirstById(id);
        user.setActiveTemplate(activeTemplate);
        userRepository.save(user);
        return "change-active-template-confirmation";
    }

    @GetMapping("/portfolioitems/{id}/image")
    public ResponseEntity<Object> downloadPortfolioItemImage(@PathVariable long id) throws SQLException {
        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(id);
        System.out.println("La encontre");
        if (portfolioItem.isPresent() && portfolioItem.get().getPreviewImg() != null) {

            Resource file = new InputStreamResource(portfolioItem.get().getPreviewImg().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(portfolioItem.get().getPreviewImg().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/settings/edit/account/set/new/info")
    public String setNewInfoCurrentUser(Model model, HttpServletRequest request, User user) throws IOException {
        userService.updateUser(user,user.getid());
        return "settings-edit-account";
    }
}
