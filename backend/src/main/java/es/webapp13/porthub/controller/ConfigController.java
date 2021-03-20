package es.webapp13.porthub.controller;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    private TemplateService templateService;

    @Autowired
    private PortfolioItemService portfolioItemService;

    @GetMapping("/settings/edit/account")
    public String studentEditAccountLink(Model model, HttpServletRequest request) {
        model.addAttribute("active_main", true);
        Principal principal = request.getUserPrincipal();
        User user = userService.findUser(principal.getName());
        model.addAttribute("user", user);
        return "settings-edit-account";
    }


    @GetMapping("/settings/edit/account/portfolioitems")
    public String studentEditAccountPortfolioitemsLink(Model model, HttpServletRequest request, Pageable pageable) {
        model.addAttribute("active_notifications", true);
        Principal principal = request.getUserPrincipal();
        User user = userService.findUser(principal.getName());

        Page<PortfolioItem> portfolioItems = portfolioItemService.findPortfolioItems(user.getid(), pageable);
        model.addAttribute("hasNext", portfolioItems.hasNext());
        model.addAttribute("portfolioItemsPage", portfolioItems);
        return "settings-edit-account-portfolioitems";
    }


    @PostMapping("/settings/edit/account/portfolioitems/confirm")
    public String studentEditAccountNotificationsForm(Model model, HttpServletRequest request,
                                                      PortfolioItem portfolioItem, MultipartFile preImg, MultipartFile img1,
                                                      MultipartFile img2, MultipartFile img3, Pageable pageable) throws IOException {
        model.addAttribute("active_notifications", true);

        Principal principal = request.getUserPrincipal();
        User user = userService.findUser(principal.getName());

        portfolioItemService.addPortfolioItem(user.getid(), portfolioItem, preImg, img1, img2, img3);
        Page<PortfolioItem> portfolioItems = portfolioItemService.findPortfolioItems(user.getid(), pageable);
        return "confirm-portfolioitem";
    }

    @GetMapping("/settings/edit/account/{id}/deleted/portfolio-item")
    public String portfolioItemDeleteLink(@PathVariable long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userService.findUser(principal.getName());

        portfolioItemService.deletePortfolioItem(user.getid(), id);
        return "deleted-portfolioitem";
    }

    @GetMapping("/settings/edit/account/edit/portfolioitem/{userId}/{id}")
    public String portfolioItemEditLink(Model model, HttpServletRequest request, @PathVariable long id, @PathVariable String userId) {
        Principal principal = request.getUserPrincipal();
        User user = userService.findUser(principal.getName());
        if (!userId.equals(user.getid()))
            return "error";
        model.addAttribute("portfolioItem", portfolioItemService.getPortfolioItem(userId, id));
        return "settings-edit-account-edit-portfolioitem";
    }

    @PostMapping("/settings/edit/account/edit/portfolioitem/{userId}/{id}")
    public String portfolioItemEditForm(Model model, HttpServletRequest request, @PathVariable long id, @PathVariable String userId, PortfolioItem newPortfolioItem, MultipartFile preImg, MultipartFile img1, MultipartFile img2, MultipartFile img3) throws IOException, SQLException {
        Principal principal = request.getUserPrincipal();
        User user = userService.findUser(principal.getName());
        if (!userId.equals(user.getid()))
            return "error";

        portfolioItemService.updatePortfolioItem(newPortfolioItem, id, preImg, img1, img2, img3);
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
    public String userTemplatesLink(Model model) {
        model.addAttribute("templates", activeTemplateService.getActiveTemplateList());
        return "settings-edit-account-mytemplates";
    }

    @GetMapping("/set/active/template")
    public String activeTemplateLink(Model model, HttpServletRequest request, @RequestParam long id) {
        Principal principal = request.getUserPrincipal();
        User user = userService.findUser(principal.getName());
        long oldId = user.getActiveTemplate().getId();
        activeTemplateService.changeActiveTemplate(oldId, id);
        Template activeTemplate = templateService.findFirstById(id);
        user.setActiveTemplate(activeTemplate);
        userService.saveChanges(user);
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

    @GetMapping("/portfolioitems/{id}/image1")
    public ResponseEntity<Object> downloadPortfolioItemImage1(@PathVariable long id) throws SQLException {
        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(id);
        System.out.println("La encontre");
        if (portfolioItem.isPresent() && portfolioItem.get().getImage1() != null) {

            Resource file = new InputStreamResource(portfolioItem.get().getImage1().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(portfolioItem.get().getImage1().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/portfolioitems/{id}/image2")
    public ResponseEntity<Object> downloadPortfolioItemImage2(@PathVariable long id) throws SQLException {
        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(id);
        System.out.println("La encontre");
        if (portfolioItem.isPresent() && portfolioItem.get().getImage2() != null) {

            Resource file = new InputStreamResource(portfolioItem.get().getImage2().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(portfolioItem.get().getImage2().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/portfolioitems/{id}/image3")
    public ResponseEntity<Object> downloadPortfolioItemImage3(@PathVariable long id) throws SQLException {
        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(id);
        System.out.println("La encontre");
        if (portfolioItem.isPresent() && portfolioItem.get().getImage3() != null) {

            Resource file = new InputStreamResource(portfolioItem.get().getImage3().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(portfolioItem.get().getImage3().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/profilePhoto/{id}")
    public ResponseEntity<Object> downloadProfilePhoto(@PathVariable String id, HttpServletRequest request) throws SQLException {
        System.out.println("La encontre");
        User user = userService.findUser(id);
        if (user.getProfilePhoto() != null) {

            Resource file = new InputStreamResource(user.getProfilePhoto().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(user.getProfilePhoto().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/settings/edit/account/set/new/info")
    public String setNewInfoCurrentUser(Model model, HttpServletRequest request, User user, MultipartFile profileImg) throws IOException, SQLException {

        userService.updateUser(user, user.getid(), profileImg);

        return "update-profile-confirmation";
    }

}
