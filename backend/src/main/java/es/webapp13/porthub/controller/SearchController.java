package es.webapp13.porthub.controller;


import es.webapp13.porthub.service.SearchService;
import es.webapp13.porthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public String searchLink(Model model) {
        model.addAttribute("active_search", true);
        model.addAttribute("users", searchService.getUsers());
        return "search";
    }

    @GetMapping("/search-ingeniero")
    public String searchLinkEngineer(Model model) {
        model.addAttribute("active_engineer", true);
        model.addAttribute("users", searchService.getUsersByCategory("Ingeniero"));
        return "search";
    }

    @GetMapping("/search-diseñador")
    public String searchLinkDesigner(Model model) {
        model.addAttribute("active_search", true);
        model.addAttribute("users", searchService.getUsersByCategory("Diseñador"));
        return "search";
    }

    @GetMapping("/search-fotografo")
    public String searchLinkPhotographer(Model model) {
        model.addAttribute("active_search", true);
        model.addAttribute("users", searchService.getUsersByCategory("Fotografo"));
        return "search";
    }

    @GetMapping("/search-empresario")
    public String searchLinkBusinessman (Model model) {
        model.addAttribute("active_search", true);
        model.addAttribute("users", searchService.getUsersByCategory("Empresario"));
        return "search";
    }


}
