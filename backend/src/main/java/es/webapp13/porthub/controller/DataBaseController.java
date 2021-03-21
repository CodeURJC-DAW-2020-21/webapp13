package es.webapp13.porthub.controller;


import es.webapp13.porthub.repository.PortfolioItemRepository;
import es.webapp13.porthub.repository.TemplateRepository;
import es.webapp13.porthub.repository.UserRepository;
import es.webapp13.porthub.model.*;

import es.webapp13.porthub.service.ActiveTemplateService;
import es.webapp13.porthub.service.PurchasedTemplateService;
import es.webapp13.porthub.service.UserService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private PurchasedTemplateService purchasedTemplateService;

    @Autowired
    private ActiveTemplateService activeTemplateService;

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

        User user = new User("educam17", "Eduardo", "Camero", "educam123@gmail.com",passwordEncoder.encode("pass"), "618 99 55 66",
                "www.urjc.es", "Madrid", "Grado en Ingeniería Informática", "freelance", "Persona trabajadora y constante", "Ingeniero", free,new Date(161588600),portfolioItemList, "USER");
        userRepository.save(user);
        user.getTemplates().add(free);
        activeTemplateService.init(user.getTemplates(), user.getActiveTemplate());
        purchasedTemplateService.init(user.getTemplates());



        userRepository.save(new User("lmessi10", "Lionel", "Messi", "lionelamessi10@gmail.com",passwordEncoder.encode("1234"), "635 890 173",
                "www.pinterest.com", "Barcelona", "Técnico de imagen y sonido", "freelance2", "Persona con rápido aprendizaje y adaptación", "Fotografo", premium, new Date(162598600),"USER", "ADMIN"));

        userRepository.save(new User("ppdomi3", "José", "Dominguez", "ppdominguez14@hotmail.com",passwordEncoder.encode("pass"), "phoneNumber",
                "www.radiomarca.com", "Valencia", "Grado de Ingeniería de Telecomunicaciones", "freelance", "Amplia experiencia laboral trabajando para empresas como Telefonica", "Ingeniero", free,new Date(141598600), "USER"));

        userRepository.save(new User("jdelgado00", "Juan", "Delgado", "jdelgado2020@gmail.com",passwordEncoder.encode("pass"), "624 563 789",
                "www.twitch.com", "San Sebastián", "Grado de Ingenieria de Caminos", "freelance", "description", "Ingeniero", free,new Date(151598600), "USER"));

        userRepository.save(new User("dperez15", "David", "Perez", "davidperez10@yahoo.com",passwordEncoder.encode("pass"), "623 768 654",
                "www.youtube.com", "París", "Grado de Diseño de Interiores", "freelance", "description", "Diseñador", free,new Date(163598600), "USER"));

        userRepository.save(new User("csanchez85", "Carlos", "Sanchez", "carlossanchez99@oulook.com",passwordEncoder.encode("pass"), "629 798 657",
                "www.bitpanda.com", "Valladolid", "Grado de Ingenieria de la Energía", "freelance", "description", "Ingeniero", free,new Date(181598600), "USER"));

        userRepository.save(new User("rogonza987", "Rodrigo", "Gonzalez", "rodrigonzalez7@gmail.com",passwordEncoder.encode("pass"), "604 347 927",
                "www.telegram.com", "Toledo", "Grado de Ingeniería Medioambiental", "freelance", "", "Ingeniero", free,new Date(167598600), "USER"));

        userRepository.save(new User("anitarom56", "Ana", "Romero", "anaromeroest79@gmail.com",passwordEncoder.encode("pass"), "690 216 237",
                "www.instagram.com", "Cáceres", "Grado de Ingenieria Electrica", "freelance", "description", "Ingeniero", free,new Date(169598600), "USER"));

        userRepository.save(new User("siiil99", "Silvia", "Ballesteros", "silviaballesteros98@outlook.com",passwordEncoder.encode("pass"), "639 289 038",
                "www.tuenti.com", "Badajoz", "Grado de Fundamentos de la fotografia", "freelance", "description", "Fotografo", free,new Date(161298600), "USER"));

        userRepository.save(new User("lauradiaz67", "Laura", "Díaz", "lauradiezmad20@telefonica.net",passwordEncoder.encode("pass"), "699 236 546",
                "www.facebook.com", "Londres", "Grado de Ingeniería del software", "freelance", "description", "Ingeniero", free,new Date(161558600), "USER"));

        userRepository.save(new User("bertacabello25", "Berta", "Cabello", "bertacabello50@gmail.com",passwordEncoder.encode("pass"), "634 847 935",
                "www.snapchat.com", "Vigo", "Grado de Ingeniería Geologica", "freelance", "description", "Ingeniero", free, new Date(166598600),"USER"));

        userRepository.save(new User("crisheredia10", "Cristina", "Heredia", "crisheredia11@hotmail.com",passwordEncoder.encode("pass"), "619 025 549",
                "www.elmundo.com", "A Coruña", "Doble grado de Ingenieria Informatica y Software", "freelance", "description", "Ingeniero", free,new Date(111598600), "USER"));

        userRepository.save(new User("leoguti156", "Leopoldo", "Gutierrez", "leogutierrez1996@yahoo.es",passwordEncoder.encode("pass"), "618 289 823",
                "www.as.com", "Mallorca", "Grado de Administración y Dirección de Empresas", "freelance", "description", "Empresario", free,new Date(121508600), "USER"));

        userRepository.save(new User("sergiosp21", "Sergio", "Perez", "sergioperezsp@gmail.com",passwordEncoder.encode("pass"), "602 978 945 ",
                "www.linkedn.com", "Ibiza", "Grado de Comunicacion Audiovisual", "freelance", "description", "Fotografia", free,new Date(191598600), "USER"));

        userRepository.save(new User("dionig2", "Dionisio", "Guerrero", "dionielguerrero37@outlook.com",passwordEncoder.encode("pass"), "629 783 292",
                "www.spotify.com", "Murcia", "Grado de Ingeniera Matematica", "freelance", "description", "Ingeniero", free,new Date(161398600), "USER"));

        userRepository.save(new User("manuarias89", "Manuel", "Arias", "manarias567@gmail.com",passwordEncoder.encode("pass"), "627 472 343",
                "www.discord.com", "Cartagena", "Doble grado de Ingenieria Informatica y Medicina", "freelance", "description", "Ingeniero", free,new Date(161598200), "USER"));

        userRepository.save(new User("anselagudo74", "Anselmo", "Agudo", "anselagudo3@gmail.com",passwordEncoder.encode("pass"), "610 497 823",
                "www.rtve.es", "León", "Grado de Ingenieria Aeroespacial", "freelance", "description", "Ingeniero", free,new Date(171593600), "USER"));

        userRepository.save(new User("ivancanosp", "Ivan", "Cano", "ivancanosyp@grimey.es",passwordEncoder.encode("pass"), "632 708 916",
                "www.whatsapp.com", "Soria", "Doble grado de Administracion y Direccion de Empresas y Derecho", "freelance", "description", "Empresario", free,new Date(161598600), "USER"));

        userRepository.save(new User("costantonio6", "Antonio", "Costa", "tonicosta45@telfonica.net",passwordEncoder.encode("pass"), "602 472 938",
                "www.twitter.com", "Ciudad Real", "Grado de Economia", "freelance", "description", "Empresario", free,new Date(261598600), "USER"));

        userRepository.save(new User("a.lopez.76", "Abraham", "Lopez", "abrahamlopez12@gmail.com",passwordEncoder.encode("pass"), "619 749 853",
                "www.ask.fm", "Sevilla", "Grado de Ingenieria de Algoritmos", "freelance", "description", "Ingeniero", free,new Date(169598600), "USER"));


    }
}


