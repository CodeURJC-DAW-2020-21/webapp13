package es.webapp13.porthub.controller;

import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.SearchService;
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

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Optional;

@Controller
public class SearchController {


    @Autowired
    private SearchService searchService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public String searchLink(Model model,Pageable pageable) {
        model.addAttribute("active_all", true);
        Page<User> userPage = userService.findUsers(pageable);
        model.addAttribute("hasNext",userPage.hasNext());
        model.addAttribute("category","Todas");
        model.addAttribute("users", userPage);

        return "search";
    }

    @GetMapping("/searchbar")
    public String searchBarLink(Model model){
        model.addAttribute("category","Busqueda");
        return "search";
    }

    @PostMapping("/searchbar")
    public String searchBarForm(Model model,String search,Pageable pageable){
        Page<User> userPage = searchService.getUsersBySearch(search,pageable);
        model.addAttribute("hasNext",userPage.hasNext());
        model.addAttribute("category","Busqueda");
        model.addAttribute("users", userPage);
        return "search";
    }

    @GetMapping("/search/ingenieria")
    public String searchLinkEngineer(Model model,Pageable pageable) {
        model.addAttribute("active_engineer", true);
        Page<User> userPage = searchService.getUsersByCategory("Ingeniero",pageable);
        model.addAttribute("hasNext",userPage.hasNext());
        model.addAttribute("category","Ingeniería");
        model.addAttribute("users", userPage);
        return "search";
    }

    @GetMapping("/search/diseño")
    public String searchLinkDesigner(Model model,Pageable pageable) {
        model.addAttribute("active_designer", true);
        Page<User> userPage = searchService.getUsersByCategory("Diseñador",pageable);
        model.addAttribute("hasNext",userPage.hasNext());
        model.addAttribute("category","Diseño");
        model.addAttribute("users", userPage);
        return "search";
    }

    @GetMapping("/search/fotografia")
    public String searchLinkPhotographer(Model model,Pageable pageable) {
        model.addAttribute("active_photographer", true);
        Page<User> userPage = searchService.getUsersByCategory("Fotografo",pageable);
        model.addAttribute("hasNext",userPage.hasNext());
        model.addAttribute("category","Fotografía");
        model.addAttribute("users", userPage);
        return "search";
    }

    @GetMapping("/search/empresa")
    public String searchLinkBusinessman(Model model,Pageable pageable) {
        model.addAttribute("active_businessman", true);
        Page<User> userPage = searchService.getUsersByCategory("Empresario",pageable);
        model.addAttribute("hasNext",userPage.hasNext());
        model.addAttribute("category","Empresa");
        model.addAttribute("users", userPage);
        return "search";
    }


}
