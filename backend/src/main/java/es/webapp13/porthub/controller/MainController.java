package es.webapp13.porthub.controller;

import es.webapp13.porthub.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String indexLink(Model model) {
        model.addAttribute("users", userService);
        return "index";
    }


   /* @GetMapping("/error")
    public String errorLink() {
        return "error";
    }*/


}
