package es.webapp13.porthub.controller;

import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class EntitiesController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/")
    public Page<User> getUsers(Pageable page) {
        return userService.findUsersPage(page);
    }

}
