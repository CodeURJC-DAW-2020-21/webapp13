package es.webapp13.porthub.controller;

import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.SearchService;
import es.webapp13.porthub.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class SearchController {


    private final SearchService searchService;

    private final UserService userService;

    public SearchController(SearchService searchService, UserService userService) {
        this.searchService = searchService;
        this.userService = userService;
    }

    @GetMapping("/search")
    public String searchLink(Model model, Pageable pageable) {
        Page<User> userPage = userService.findUsers(pageable);
        addAttributes(model, userPage, "Todas", "active_all");
        return "search";
    }

    @GetMapping("/searchbar")
    public String searchBarLink(Model model) {
        model.addAttribute("category", "Busqueda");
        return "search";
    }

    @PostMapping("/searchbar")
    public String searchBarForm(Model model, String search, Pageable pageable) {
        Page<User> userPage = searchService.getUsersBySearch(search, pageable);
        addAttributes(model, userPage, "Búsqueda", "active_all");
        return "search";
    }

    @GetMapping("/search/ingenieria")
    public String searchLinkEngineer(Model model, Pageable pageable) {
        Page<User> userPage = searchService.getUsersByCategory("Ingeniero", pageable);
        addAttributes(model, userPage, "Ingeniería", "active_engineer");
        return "search";
    }

    @GetMapping("/search/diseño")
    public String searchLinkDesigner(Model model, Pageable pageable) {
        Page<User> userPage = searchService.getUsersByCategory("Diseñador", pageable);
        addAttributes(model, userPage, "Diseño", "active_designer");
        return "search";
    }

    @GetMapping("/search/fotografia")
    public String searchLinkPhotographer(Model model, Pageable pageable) {
        Page<User> userPage = searchService.getUsersByCategory("Fotografo", pageable);
        addAttributes(model, userPage, "Fotografía", "active_photographer");
        return "search";
    }

    @GetMapping("/search/empresa")
    public String searchLinkBusinessman(Model model, Pageable pageable) {
        Page<User> userPage = searchService.getUsersByCategory("Empresario", pageable);
        addAttributes(model, userPage, "Empresa", "active_businessman");
        return "search";
    }

    /**
     * Add common attributes to the views
     *
     * @param model    Model page
     * @param userPage Page with the users
     * @param category Category of the search
     * @param active   Button to set class to active
     */
    private void addAttributes(Model model, Page<User> userPage, String category, String active) {
        model.addAttribute(active, true);
        model.addAttribute("hasNext", userPage.hasNext());
        model.addAttribute("category", category);
        model.addAttribute("users", userPage);
    }


}
