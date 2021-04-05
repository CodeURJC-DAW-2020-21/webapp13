package es.webapp13.porthub.controller;

import es.webapp13.porthub.model.PortfolioItem;
import es.webapp13.porthub.model.Template;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.PortfolioItemService;
import es.webapp13.porthub.service.UserService;
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

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Optional;

@Controller
public class TemplateController {

    private final UserService userService;

    private final PortfolioItemService portfolioItemService;

    public TemplateController(UserService userService, PortfolioItemService portfolioItemService) {
        this.userService = userService;
        this.portfolioItemService = portfolioItemService;
    }

    @GetMapping("/profile")
    public String profileLink(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userService.findById(principal.getName()).orElseThrow();
        model.addAttribute("portfolioItems", user.getPortfolioItems());
        Template template = user.getActiveTemplate();
        return template.getHtmlPath();
    }

    @GetMapping("/templates/free/index")
    public String templateFreeLink() {
        return "templates/free/index";
    }


    @GetMapping("/templates/premium/index")
    public String templatePremiumLink() {
        return "templates/premium/index";
    }


    @GetMapping("/template/premium/{userId}/portfolioitem/{itemId}")
    public String templatePremiumPortfolioItemLink(Model model, @PathVariable String userId, @PathVariable long itemId) {
        model.addAttribute("portfolioUser", userService.findById(userId).orElseThrow());
        model.addAttribute("portfolioItem", portfolioItemService.getPortfolioItem(userId, itemId));
        return "templates/premium/portfolio-item";
    }

    @GetMapping("/template/free/{userId}/portfolioitem/{itemId}")
    public String templateFreePortfolioItemLink(Model model, @PathVariable String userId, @PathVariable long itemId) {
        model.addAttribute("portfolioUser", userService.findById(userId).orElseThrow());
        model.addAttribute("portfolioItem", portfolioItemService.getPortfolioItem(userId, itemId));
        return "templates/free/portfolio-item";
    }

    @GetMapping("/template/{id}")
    public String templateFromSearchLink(Model model, @PathVariable String id, HttpServletRequest request, Pageable pageable) {
        User portfolioUser = userService.findById(id).orElseThrow();
        model.addAttribute("portfolioUser", portfolioUser);
        model.addAttribute("external", true);

        Page<PortfolioItem> portfolioItems = portfolioItemService.findPortfolioItems(portfolioUser.getId(), pageable);
        model.addAttribute("hasNext", portfolioItems.hasNext());
        model.addAttribute("portfolioItemsPage", portfolioItems);

        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            User activeUser = userService.findById(principal.getName()).orElseThrow();
            if (activeUser != portfolioUser) {
                model.addAttribute("chat", true);
            }
        } else {
            model.addAttribute("chat", true);
        }
        return userService.findTemplateHtmlPath(id);
    }

    @GetMapping("/users/{id}/image")
    public ResponseEntity<Object> downloadPortfolioItemImage(@PathVariable String id) throws SQLException {
        Optional<User> user = userService.findById(id);
        if (user.isPresent() && user.get().getProfilePhoto() != null) {
            Resource file = new InputStreamResource(user.get().getProfilePhoto().getBinaryStream());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(user.get().getProfilePhoto().length()).body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
