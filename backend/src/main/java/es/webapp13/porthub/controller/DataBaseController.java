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
        userRepository.save(new User("cfres","Cristian","De Gracia"));
    }

}
