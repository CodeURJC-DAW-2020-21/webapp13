package es.webapp13.porthub.controller;


import es.webapp13.porthub.repository.PortfolioItemRepository;
import es.webapp13.porthub.repository.TemplateRepository;
import es.webapp13.porthub.repository.UserRepository;
import es.webapp13.porthub.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;


@Controller
public class DataBaseController implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PortfolioItemRepository portfolioItemRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TemplateRepository templateRepository;

    @Override
    public void run(String... args) throws Exception {
        Template free = new Template("/templates/free/index","Free",0, true, "Un diseño orientado a una clara visualización de tu trabajo");
        Template premium = new Template("/templates/premium/index","Premium",20, false, "Un diseño de corte serio dirigido a empresas");
        templateRepository.save(free);
        templateRepository.save(premium);

        PortfolioItem portfolioItem1 = new PortfolioItem("id","proyecto1", "Description", "Category", "Client", "URL", new Date(161598600));
        portfolioItemRepository.save(portfolioItem1);
        PortfolioItem portfolioItem2 = new PortfolioItem("id","proyecto2", "Description", "Category", "Client", "URL", new Date(161598600));
        portfolioItemRepository.save(portfolioItem2);
        PortfolioItem portfolioItem3 = new PortfolioItem("id","proyecto3", "Description", "Category", "Client", "URL", new Date(161598600));
        portfolioItemRepository.save(portfolioItem3);
        PortfolioItem portfolioItem4 = new PortfolioItem("id","proyecto4", "Description", "Category", "Client", "URL", new Date(161598600));
        portfolioItemRepository.save(portfolioItem4);
        PortfolioItem portfolioItem5 = new PortfolioItem("id","proyecto5", "Description", "Category", "Client", "URL", new Date(161598600));
        portfolioItemRepository.save(portfolioItem5);
        PortfolioItem portfolioItem6 = new PortfolioItem("id","proyecto6", "Description", "Category", "Client", "URL", new Date(161598600));
        portfolioItemRepository.save(portfolioItem6);
        PortfolioItem portfolioItem7 = new PortfolioItem("id","proyecto7", "Description", "Category", "Client", "URL", new Date(161598600));
        portfolioItemRepository.save(portfolioItem7);

        List<PortfolioItem> portfolioItemList = new LinkedList<>();
        portfolioItemList.add(portfolioItem1);
        portfolioItemList.add(portfolioItem2);
        portfolioItemList.add(portfolioItem3);
        portfolioItemList.add(portfolioItem4);
        portfolioItemList.add(portfolioItem5);
        portfolioItemList.add(portfolioItem6);
        portfolioItemList.add(portfolioItem7);

        userRepository.save(new User("id", "name1", "surname", "email@email",passwordEncoder.encode("pass"), "618 99 55 66",
                "www.web.es", "Madrid", "Grado en Ingeniería Informática", "freelance", "description", "Ingeniero", free,new Date(161598600),portfolioItemList, "USER"));

        userRepository.save(new User("id2", "name2", "surname2", "email2",passwordEncoder.encode("1234"), "phoneNumber2",
                "website2", "city2", "degree2", "freelance2", "description2", "Fotografo", premium, new Date(161598600),"USER", "ADMIN"));

        userRepository.save(new User("id23", "name3", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "Ingeniero", free,new Date(161598600), "USER"));

        userRepository.save(new User("id3", "name4", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "Ingeniero", free,new Date(161598600), "USER"));

        userRepository.save(new User("id4", "name5", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "Ingeniero", free,new Date(161598600), "USER"));

        userRepository.save(new User("id5", "name6", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "Ingeniero", free,new Date(161598600), "USER"));

        userRepository.save(new User("id6", "name7", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "Ingeniero", free,new Date(161598600), "USER"));

        userRepository.save(new User("id7", "name8", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "Ingeniero", free,new Date(161598600), "USER"));

        userRepository.save(new User("id8", "name9", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "Ingeniero", free,new Date(161598600), "USER"));

        userRepository.save(new User("id9", "name10", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "Ingeniero", free,new Date(161598600), "USER"));

        userRepository.save(new User("id10", "name11", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "Ingeniero", free, new Date(161598600),"USER"));

        userRepository.save(new User("id234", "name12", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "Ingeniero", free,new Date(161598600), "USER"));

        userRepository.save(new User("id35", "name13", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "Ingeniero", free,new Date(161598600), "USER"));

        userRepository.save(new User("id46", "name14", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "Ingeniero", free,new Date(161598600), "USER"));

        userRepository.save(new User("id56", "name15", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "Ingeniero", free,new Date(161598600), "USER"));

        userRepository.save(new User("id66", "name16", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "Ingeniero", free,new Date(161598600), "USER"));

        userRepository.save(new User("id76", "name17", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "Ingeniero", free,new Date(161598600), "USER"));

        userRepository.save(new User("id88", "name18", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "Ingeniero", free,new Date(161598600), "USER"));

        userRepository.save(new User("id99", "name19", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "Ingeniero", free,new Date(161598600), "USER"));

        userRepository.save(new User("id108", "name20", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "Ingeniero", free,new Date(161598600), "USER"));


    }
}


