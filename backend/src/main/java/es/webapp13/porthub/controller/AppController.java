package es.webapp13.porthub.controller;

import es.webapp13.porthub.model.PortfolioItem;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.UserService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;


@Controller
public class AppController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String indexLink(Model model) {
        return "index";
    }

    @GetMapping("/search")
    public String searchLink(Model model) {
        model.addAttribute("active_search", true);
        return "search";
    }

    @GetMapping("/templates/premium/index")
    public String templateLink(Model model) {
        //El usuario que he metido al iniciar el programa tiene como valor para el atributo
        //userName, la palabra userName.
        //En siguientes versiones no hara falta buscarlo dado que tendremos algun atributo
        //para guardar al usuario activo
        User user = userService.findUser("username");
        model.addAttribute("name", user.getName());
        model.addAttribute("surname", user.getSurname());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("phoneNumber", user.getPhoneNumber());
        model.addAttribute("bornDate", user.getBornDate());
        return "templates/premium/index";
    }

    @GetMapping("/templates/premium/portfolioitem")
    public String templatePortfolioItemLink(Model model) {
        return "templates/premium/portfolio-item";
    }

    @GetMapping("/shop")
    public String shopLink(Model model) {
        model.addAttribute("active_shop", true);
        return "shop";
    }

    @GetMapping("/student-edit-account")
    public String studentEditAccountLink(Model model) {
        model.addAttribute("active_main", true);
        return "student-edit-account";
    }

    @GetMapping("/student-edit-account-notifications")
    public String studentEditAccountNotificationsLink(Model model) {
        model.addAttribute("active_notifications", true);
        return "student-edit-account-notifications";
    }


    @PostMapping("/student-edit-account-notifications")
    public String studentEditAccountNotificationsForm(Model model, PortfolioItem portfolioItem) {
        model.addAttribute("active_notifications", true);
        userService.addPortfolioItem("cfres",portfolioItem);
        return "student-edit-account-notifications";
    }


    @GetMapping("/student-edit-account-password")
    public String studentEditAccountPasswordLink(Model model) {
        model.addAttribute("active_password", true);
        return "student-edit-account-password";
    }

    @GetMapping("/student-edit-account-profile")
    public String studentEditAccountProfileLink(Model model) {
        model.addAttribute("active_profile", true);
        return "student-edit-account-profile";
    }

    @GetMapping("/login")
    public String loginLink(Model model) {
        return "login";
    }

    @GetMapping("/reset-password")
    public String forgottenPasswordLink(Model model) {
        return "reset-password";
    }

    @GetMapping("/signup")
    public String signupLink(Model model) {
        return "signup";
    }

    @PostMapping("/signup-confirmation")
    public String signupConfirmationLink(Model model, User user, @RequestParam MultipartFile imageFile) throws IOException {
        userService.createUser(user);
        return "signup-confirmation";
    }

    @GetMapping("/admin")
    public String adminLink(Model model) {
        return "admin";
    }

    @GetMapping("/banned-users")
    public String adminBannedUsersLink(Model model) {
        return "admin-banned-users";
    }

    @GetMapping("/app-graphics")
    public String adminAppGraphicsLink(Model model) {
        return "admin-app-graphics";
    }

    @GetMapping("/templates-list")
    public String adminTemplatesListLink(Model model) {
        return "admin-templates-list";
    }


}
