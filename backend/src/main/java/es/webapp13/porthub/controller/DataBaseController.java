package es.webapp13.porthub.controller;

import es.webapp13.porthub.model.*;
import es.webapp13.porthub.service.*;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;


@Controller
public class DataBaseController implements CommandLineRunner {


    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final TemplateService templateService;

    private final PurchasedTemplateService purchasedTemplateService;

    private final ActiveTemplateService activeTemplateService;

    private final PortfolioItemService portfolioItemService;

    public DataBaseController(PasswordEncoder passwordEncoder, UserService userService, TemplateService templateService, PurchasedTemplateService purchasedTemplateService, ActiveTemplateService activeTemplateService, PortfolioItemService portfolioItemService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.templateService = templateService;
        this.purchasedTemplateService = purchasedTemplateService;
        this.activeTemplateService = activeTemplateService;
        this.portfolioItemService = portfolioItemService;
    }

    @Override
    public void run(String... args) throws Exception {
        Template free = new Template("templates/free/index", "Free", 0, true, "Un diseño orientado a una clara visualización de tu trabajo");
        Template premium = new Template("templates/premium/index", "Premium", 20, false, "Un diseño de corte serio dirigido a empresas");
        templateService.save(free);
        templateService.save(premium);

        PortfolioItem portfolioItem1 = new PortfolioItem("id", "proyecto1", "Description", "Category", "Client", "URL", new Date(161598600));
        this.setPortfolioItemImage(portfolioItem1, "/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg");
        portfolioItemService.save(portfolioItem1);


        PortfolioItem portfolioItem2 = new PortfolioItem("id", "proyecto2", "Description", "Category", "Client", "URL", new Date(161598600));
        this.setPortfolioItemImage(portfolioItem2, "/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg");
        portfolioItemService.save(portfolioItem2);

        PortfolioItem portfolioItem3 = new PortfolioItem("id", "proyecto3", "Description", "Category", "Client", "URL", new Date(161598600));
        this.setPortfolioItemImage(portfolioItem3, "/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg");
        portfolioItemService.save(portfolioItem3);


        PortfolioItem portfolioItem4 = new PortfolioItem("id", "proyecto4", "Description", "Category", "Client", "URL", new Date(161598600));
        this.setPortfolioItemImage(portfolioItem4, "/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg");
        portfolioItemService.save(portfolioItem4);

        PortfolioItem portfolioItem5 = new PortfolioItem("id", "proyecto5", "Description", "Category", "Client", "URL", new Date(161598600));
        this.setPortfolioItemImage(portfolioItem5, "/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg");
        portfolioItemService.save(portfolioItem5);

        PortfolioItem portfolioItem6 = new PortfolioItem("id", "proyecto6", "Description", "Category", "Client", "URL", new Date(161598600));
        this.setPortfolioItemImage(portfolioItem6, "/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg");
        portfolioItemService.save(portfolioItem6);

        PortfolioItem portfolioItem7 = new PortfolioItem("id", "proyecto7", "Description", "Category", "Client", "URL", new Date(161598600));
        this.setPortfolioItemImage(portfolioItem7, "/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg");
        portfolioItemService.save(portfolioItem7);

        List<PortfolioItem> portfolioItemList = new LinkedList<>();
        portfolioItemList.add(portfolioItem1);
        portfolioItemList.add(portfolioItem2);
        portfolioItemList.add(portfolioItem3);
        portfolioItemList.add(portfolioItem4);
        portfolioItemList.add(portfolioItem5);
        portfolioItemList.add(portfolioItem6);
        portfolioItemList.add(portfolioItem7);

        User user = new User("id", "José Luis", "Martinez Almeida", "joselu@pp.com", passwordEncoder.encode("pass"), 51,"618 99 55 66",
                "www.web.es", "Madrid", "Política", "No", "Abogado del Estado y alcalde de Madrid desde 2019", "Empresario", free, new Date(1669176000), portfolioItemList, "USER");
        String dateStr = "2021-01-1";
        Date date = Date.valueOf(dateStr);
        user.setCreationDate(date);
        this.setUserImage(user, "/static/app/assets/images/almeida.jpg");
        user.getTemplates().add(free);
        user.getTemplates().add(premium);
        user.setActiveTemplate(free);
        activeTemplateService.init(user.getTemplates(), user.getActiveTemplate());
        purchasedTemplateService.create(user.getTemplates());
        userService.save(user);

        User user1 = new User("admin", "Roberto", "Fernánndez", "admin@gmail.com",passwordEncoder.encode("admin"), 51,"635 890 173",
                "www.admin.com", "admin", "admin", "admin", "admin", "Fotografo", premium, new Date(162598600),"USER", "ADMIN");
        String dateStr1 = "2021-02-1";
        Date date1 = Date.valueOf(dateStr1);
        user1.setCreationDate(date1);
        this.setUserImage(user1, "/static/app/assets/images/people/110/guy-1.jpg");
        user1.getTemplates().add(free);
        user1.setActiveTemplate(free);
        activeTemplateService.init(user1.getTemplates(), user1.getActiveTemplate());
        purchasedTemplateService.create(user1.getTemplates());
        userService.save(user1);

        User user2 = new User("lmessi10", "Lionel", "Messi", "lionelamessi10@gmail.com",passwordEncoder.encode("1234"), 51,"635 890 173",
                "www.pinterest.com", "Barcelona", "Técnico de imagen y sonido", "freelance2", "Persona con rápido aprendizaje y adaptación", "Fotografo", premium, new Date(162598600),"USER", "ADMIN");
        String dateStr2 = "2021-03-1";
        Date date2 = Date.valueOf(dateStr2);
        user2.setCreationDate(date2);
        this.setUserImage(user2, "/static/app/assets/images/people/110/guy-2.jpg");
        user2.getTemplates().add(free);
        user2.setActiveTemplate(free);
        activeTemplateService.init(user2.getTemplates(), user2.getActiveTemplate());
        purchasedTemplateService.create(user2.getTemplates());
        userService.save(user2);

        User user3 = new User("jdelgado00", "Juan", "Delgado", "jdelgado2020@gmail.com",passwordEncoder.encode("pass"), 51,"624 563 789",
                "www.twitch.com", "San Sebastián", "Ingenieria de Caminos", "freelance", "description", "Ingeniero", free,new Date(151598600), "USER");
        String dateStr3 = "2021-04-1";
        Date date3 = Date.valueOf(dateStr3);
        user3.setCreationDate(date3);
        this.setUserImage(user3, "/static/app/assets/images/people/110/guy-3.jpg");
        user3.getTemplates().add(free);
        user3.getTemplates().add(premium);
        user3.setActiveTemplate(free);
        activeTemplateService.init(user3.getTemplates(), user3.getActiveTemplate());
        purchasedTemplateService.create(user3.getTemplates());
        userService.save(user3);

        User user4 = new User("dperez15", "David", "Perez", "davidperez10@yahoo.com",passwordEncoder.encode("pass"), 51,"623 768 654",
                "www.youtube.com", "París", "Diseño de Interiores", "freelance", "description", "Diseñador", free,new Date(163598600), "USER");
        String dateStr4 = "2021-05-1";
        Date date4 = Date.valueOf(dateStr4);
        user4.setCreationDate(date4);
        this.setUserImage(user4, "/static/app/assets/images/people/110/guy-4.jpg");
        user4.getTemplates().add(free);
        user4.setActiveTemplate(free);
        activeTemplateService.init(user4.getTemplates(), user4.getActiveTemplate());
        purchasedTemplateService.create(user4.getTemplates());
        userService.save(user4);

        User user5 = new User("csanchez85", "Carlos", "Sanchez", "carlossanchez99@oulook.com",passwordEncoder.encode("pass"), 51,"629 798 657",
                "www.bitpanda.com", "Valladolid", "Ingenieria de la Energía", "freelance", "description", "Ingeniero", free,new Date(181598600), "USER");
        String dateStr5 = "2021-06-1";
        Date date5 = Date.valueOf(dateStr5);
        user5.setCreationDate(date5);
        this.setUserImage(user5, "/static/app/assets/images/people/110/guy-5.jpg");
        user5.getTemplates().add(free);
        user5.getTemplates().add(premium);
        user5.setActiveTemplate(free);
        activeTemplateService.init(user.getTemplates(), user5.getActiveTemplate());
        purchasedTemplateService.create(user5.getTemplates());
        userService.save(user5);

        User user6 = new User("rogonza987", "Rodrigo", "Gonzalez", "rodrigonzalez7@gmail.com",passwordEncoder.encode("pass"), 51,"604 347 927",
                "www.telegram.com", "Toledo", "Ingeniería Medioambiental", "freelance", "", "Ingeniero", free,new Date(167598600), "USER");
        String dateStr6 = "2021-07-1";
        Date date6 = Date.valueOf(dateStr6);
        user6.setCreationDate(date6);
        this.setUserImage(user6, "/static/app/assets/images/people/110/guy-6.jpg");
        user6.getTemplates().add(free);
        user6.getTemplates().add(premium);
        user6.setActiveTemplate(free);
        activeTemplateService.init(user6.getTemplates(), user.getActiveTemplate());
        purchasedTemplateService.create(user6.getTemplates());
        userService.save(user6);

        User user7 = new User("anitarom56", "Ana", "Romero", "anaromeroest79@gmail.com",passwordEncoder.encode("pass"), 51,"690 216 237",
                "www.instagram.com", "Cáceres", "Ingenieria Electrica", "freelance", "description", "Ingeniero", free,new Date(169598600), "USER");
        String dateStr7 = "2021-08-1";
        Date date7 = Date.valueOf(dateStr7);
        user7.setCreationDate(date7);
        this.setUserImage(user7, "/static/app/assets/images/people/110/woman-1.jpg");
        user7.getTemplates().add(free);
        user7.getTemplates().add(premium);
        user7.setActiveTemplate(free);
        activeTemplateService.init(user7.getTemplates(), user7.getActiveTemplate());
        purchasedTemplateService.create(user7.getTemplates());
        userService.save(user7);

        User user8 = new User("siiil99", "Silvia", "Ballesteros", "silviaballesteros98@outlook.com",passwordEncoder.encode("pass"), 51,"639 289 038",
                "www.tuenti.com", "Badajoz", "Fundamentos de la fotografia", "freelance", "description", "Fotografo", free,new Date(161298600), "USER");
        String dateStr8 = "2021-09-1";
        Date date8 = Date.valueOf(dateStr8);
        user8.setCreationDate(date8);
        this.setUserImage(user8, "/static/app/assets/images/people/110/woman-2.jpg");
        user8.getTemplates().add(free);
        user8.setActiveTemplate(free);
        activeTemplateService.init(user8.getTemplates(), user8.getActiveTemplate());
        purchasedTemplateService.create(user8.getTemplates());
        userService.save(user8);

        User user9 = new User("lauradiaz67", "Laura", "Díaz", "lauradiezmad20@telefonica.net",passwordEncoder.encode("pass"), 51,"699 236 546",
                "www.facebook.com", "Londres", "Ingeniería del software", "freelance", "description", "Ingeniero", free,new Date(161558600), "USER");
        String dateStr9 = "2021-10-1";
        Date date9 = Date.valueOf(dateStr9);
        user9.setCreationDate(date9);
        this.setUserImage(user9, "/static/app/assets/images/people/110/woman-3.jpg");
        user9.getTemplates().add(free);
        user9.getTemplates().add(premium);
        user9.setActiveTemplate(free);
        activeTemplateService.init(user9.getTemplates(), user9.getActiveTemplate());
        purchasedTemplateService.create(user9.getTemplates());
        userService.save(user9);

        User user10 = new User("bertacabello25", "Berta", "Cabello", "bertacabello50@gmail.com",passwordEncoder.encode("pass"), 51,"634 847 935",
                "www.snapchat.com", "Vigo", "Ingeniería Geologica", "freelance", "description", "Ingeniero", free, new Date(166598600),"USER");
        String dateStr10 = "2021-11-1";
        Date date10 = Date.valueOf(dateStr10);
        user10.setCreationDate(date10);
        this.setUserImage(user10, "/static/app/assets/images/people/110/woman-4.jpg");
        user10.getTemplates().add(free);
        user10.getTemplates().add(premium);
        user10.setActiveTemplate(free);
        activeTemplateService.init(user10.getTemplates(), user10.getActiveTemplate());
        purchasedTemplateService.create(user10.getTemplates());
        userService.save(user10);

        User user11 = new User("crisheredia10", "Cristina", "Heredia", "crisheredia11@hotmail.com",passwordEncoder.encode("pass"), 51,"619 025 549",
                "www.elmundo.com", "A Coruña", "Doble Ingenieria Informatica y Software", "freelance", "description", "Ingeniero", free,new Date(111598600), "USER");
        String dateStr11 = "2021-12-1";
        Date date11 = Date.valueOf(dateStr11);
        user11.setCreationDate(date11);
        this.setUserImage(user11, "/static/app/assets/images/people/110/woman-5.jpg");
        user11.getTemplates().add(free);
        user11.getTemplates().add(premium);
        user11.setActiveTemplate(free);
        activeTemplateService.init(user11.getTemplates(), user11.getActiveTemplate());
        purchasedTemplateService.create(user11.getTemplates());
        userService.save(user11);
    }

    public void setUserImage(User user, String classpathResource) throws IOException {
        Resource image = new ClassPathResource(classpathResource);
        user.setProfilePhoto(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }

    public void setPortfolioItemImage(PortfolioItem pItem, String rutaPrev, String ruta1, String ruta2,String ruta3) throws IOException {
        Resource imagePrev = new ClassPathResource(rutaPrev);
        pItem.setPreviewImage(BlobProxy.generateProxy(imagePrev.getInputStream(), imagePrev.contentLength()));

        Resource image1 = new ClassPathResource(ruta1);
        pItem.setImage1(BlobProxy.generateProxy(image1.getInputStream(), image1.contentLength()));

        Resource image2 = new ClassPathResource(ruta2);
        pItem.setImage2(BlobProxy.generateProxy(image2.getInputStream(), image2.contentLength()));

        Resource image3 = new ClassPathResource(ruta3);
        pItem.setImage3(BlobProxy.generateProxy(image3.getInputStream(), image3.contentLength()));
    }

}


