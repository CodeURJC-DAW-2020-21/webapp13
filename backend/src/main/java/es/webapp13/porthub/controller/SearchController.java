package es.webapp13.porthub.controller;


import es.webapp13.porthub.model.PortfolioItem;
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
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class SearchController {


    @Autowired
    private SearchService searchService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public String searchLink(Model model) {
        model.addAttribute("active_all", true);
        List<User> userList = userService.findUsers();
        model.addAttribute("total",userList.size());
        model.addAttribute("users", userList);
        return "search";
    }

    @GetMapping("/search/ingenieria")
    public String searchLinkEngineer(Model model) {
        model.addAttribute("active_engineer", true);
        List<User> userList = searchService.getUsersByCategory("Ingeniero");
        model.addAttribute("total",userList.size());
        model.addAttribute("users", userList);
        return "search";
    }

    @GetMapping("/search/diseño")
    public String searchLinkDesigner(Model model) {
        model.addAttribute("active_designer", true);
        List<User> userList = searchService.getUsersByCategory("Diseñador");
        model.addAttribute("total",userList.size());
        model.addAttribute("users", userList);
        return "search";
    }

    @GetMapping("/search/fotografia")
    public String searchLinkPhotographer(Model model) {
        model.addAttribute("active_photographer", true);
        List<User> userList = searchService.getUsersByCategory("Fotografo");
        model.addAttribute("total",userList.size());
        model.addAttribute("users", userList);
        return "search";
    }

    @GetMapping("/search/empresa")
    public String searchLinkBusinessman(Model model) {
        model.addAttribute("active_businessman", true);
        List<User> userList = searchService.getUsersByCategory("Empresario");
        model.addAttribute("total",userList.size());
        model.addAttribute("users", userList);
        return "search";
    }


    @GetMapping("/template/{id}")
    public String templateFromSearchLink(@PathVariable String id) {
        return userService.getTemplateHtmlPath(id);
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
