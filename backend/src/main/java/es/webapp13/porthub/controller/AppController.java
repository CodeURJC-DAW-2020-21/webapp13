package es.webapp13.porthub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/")
    public String indexLink(Model model) {
        return "index";
    }

    @GetMapping("/search")
    public String searchLink(Model model) {
        model.addAttribute("active_search", true);
        return "search";
    }

    @GetMapping("/shop")
    public String shopLink(Model model) {
        model.addAttribute("active_shop", true);
        return "shop";
    }

    @GetMapping("/templates/premium/index")
    public String templateLink(Model model) {
        return "templates/premium/index";
    }


}
