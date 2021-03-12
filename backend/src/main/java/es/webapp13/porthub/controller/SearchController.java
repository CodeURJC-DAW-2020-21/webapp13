package es.webapp13.porthub.controller;


import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.SearchService;
import es.webapp13.porthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SearchController {


    @Autowired
    private SearchService searchService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public String searchLink(Model model) {
        model.addAttribute("active_all", true);
        List<User> userList = searchService.getUsers();
        model.addAttribute("total",userList.size());
        model.addAttribute("users", userList);
        return "search";
    }

    @GetMapping("/search-ingeniero")
    public String searchLinkEngineer(Model model) {
        model.addAttribute("active_engineer", true);
        List<User> userList = searchService.getUsersByCategory("Ingeniero");
        model.addAttribute("total",userList.size());
        model.addAttribute("users", userList);
        return "search";
    }

    @GetMapping("/search-diseñador")
    public String searchLinkDesigner(Model model) {
        model.addAttribute("active_designer", true);
        List<User> userList = searchService.getUsersByCategory("Diseñador");
        model.addAttribute("total",userList.size());
        return "search";
    }

    @GetMapping("/search-fotografo")
    public String searchLinkPhotographer(Model model) {
        model.addAttribute("active_photographer", true);
        List<User> userList = searchService.getUsersByCategory("Fotografo");
        model.addAttribute("total",userList.size());
        return "search";
    }

    @GetMapping("/search-empresario")
    public String searchLinkBusinessman(Model model) {
        model.addAttribute("active_businessman", true);
        List<User> userList = searchService.getUsersByCategory("Empresario");
        model.addAttribute("total",userList.size());
        return "search";
    }


    @GetMapping("/template-{id}")
    public String templateFromSearchLink(@PathVariable String id) {
        return userService.getTemplateHtmlPath(id);
    }


}
