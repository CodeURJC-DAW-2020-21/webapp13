package es.webapp13.porthub.controller;

import es.webapp13.porthub.Repository.MessageRepository;
import es.webapp13.porthub.Repository.PortfolioItemRepository;
import es.webapp13.porthub.Repository.TemplateRepository;
import es.webapp13.porthub.Repository.UserRepository;
import es.webapp13.porthub.model.*;
import es.webapp13.porthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class DataBaseController implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private PortfolioItemRepository portfolioItemRepository;

    @Autowired
    private UserService loginService;

    @Override
    public void run(String... args) throws Exception {
        templateRepository.save(new Template("/templates/premium/index","Premium",20));
        templateRepository.save(new Template("/templates/free/index","Free",0));
        userRepository.save(new User("id", "name", "surname", "email", "phoneNumber", "website", "city", "degree", "freelance", "description", "job","Ingeniero"));
        userRepository.save(new User("id2", "name2", "surname2", "email2", "phoneNumber2", "website2", "city2", "degree2", "freelance2", "description2", "job2","Fotografo"));
    }

}
