package es.webapp13.porthub.controller;

import es.webapp13.porthub.model.MessageRepository;
import es.webapp13.porthub.model.PortfolioItemRepository;
import es.webapp13.porthub.model.TemplateRepository;
import es.webapp13.porthub.model.UserRepository;
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

    }

}
