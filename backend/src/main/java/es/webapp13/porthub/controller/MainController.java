package es.webapp13.porthub.controller;

import es.webapp13.porthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String indexLink(Model model) {
        model.addAttribute("users", userService);
        return "index";
    }

    @GetMapping("/error")
    public String errorLink(Model model) {
        return "error";
    }



}
