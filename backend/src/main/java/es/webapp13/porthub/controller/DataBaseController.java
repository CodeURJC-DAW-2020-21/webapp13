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
        Template free = new Template("/templates/free/index", "Free", 0, true, "Un diseño orientado a una clara visualización de tu trabajo");
        Template premium = new Template("/templates/premium/index", "Premium", 20, false, "Un diseño de corte serio dirigido a empresas");
        templateService.save(free);
        templateService.save(premium);

        PortfolioItem portfolioItem1 = new PortfolioItem("id", "proyecto1", "Description", "Category", "Client", "URL", new Date(161598600));
        this.setPortfolioItemImage(portfolioItem1, "/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg","/static/app/assets/images/almeida.jpg");
        portfolioItemService.save(portfolioItem1);


        PortfolioItem portfolioItem2 = new PortfolioItem("id", "proyecto2", "Description", "Category", "Client", "URL", new Date(161598600));
        portfolioItemService.save(portfolioItem2);

        PortfolioItem portfolioItem3 = new PortfolioItem("id", "proyecto3", "Description", "Category", "Client", "URL", new Date(161598600));
        portfolioItemService.save(portfolioItem3);

        PortfolioItem portfolioItem4 = new PortfolioItem("id", "proyecto4", "Description", "Category", "Client", "URL", new Date(161598600));
        portfolioItemService.save(portfolioItem4);

        PortfolioItem portfolioItem5 = new PortfolioItem("id", "proyecto5", "Description", "Category", "Client", "URL", new Date(161598600));
        portfolioItemService.save(portfolioItem5);

        PortfolioItem portfolioItem6 = new PortfolioItem("id", "proyecto6", "Description", "Category", "Client", "URL", new Date(161598600));
        portfolioItemService.save(portfolioItem6);

        PortfolioItem portfolioItem7 = new PortfolioItem("id", "proyecto7", "Description", "Category", "Client", "URL", new Date(161598600));
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
        this.setUserImage(user, "/static/app/assets/images/almeida.jpg");
        userService.save(user);
        user.getTemplates().add(free);
        user.getTemplates().add(premium);
        activeTemplateService.init(user.getTemplates(), user.getActiveTemplate());
        purchasedTemplateService.init(user.getTemplates());

        user = new User("admin", "Roberto", "Fernánndez", "admin@gmail.com",passwordEncoder.encode("admin"), 51,"635 890 173",
                "www.admin.com", "admin", "admin", "admin", "admin", "Fotografo", premium, new Date(162598600),"USER", "ADMIN");
        this.setUserImage(user, "/static/app/assets/images/people/110/guy-1.jpg");
        userService.save(user);
        user.getTemplates().add(free);
        activeTemplateService.init(user.getTemplates(), user.getActiveTemplate());
        purchasedTemplateService.init(user.getTemplates());

        user = new User("lmessi10", "Lionel", "Messi", "lionelamessi10@gmail.com",passwordEncoder.encode("1234"), 51,"635 890 173",
                "www.pinterest.com", "Barcelona", "Técnico de imagen y sonido", "freelance2", "Persona con rápido aprendizaje y adaptación", "Fotografo", premium, new Date(162598600),"USER", "ADMIN");
        this.setUserImage(user, "/static/app/assets/images/people/110/guy-2.jpg");
        userService.save(user);
        user.getTemplates().add(free);
        activeTemplateService.init(user.getTemplates(), user.getActiveTemplate());
        purchasedTemplateService.init(user.getTemplates());

        user = new User("jdelgado00", "Juan", "Delgado", "jdelgado2020@gmail.com",passwordEncoder.encode("pass"), 51,"624 563 789",
                "www.twitch.com", "San Sebastián", "Ingenieria de Caminos", "freelance", "description", "Ingeniero", free,new Date(151598600), "USER");
        this.setUserImage(user, "/static/app/assets/images/people/110/guy-3.jpg");
        userService.save(user);
        user.getTemplates().add(free);
        user.getTemplates().add(premium);
        activeTemplateService.init(user.getTemplates(), user.getActiveTemplate());
        purchasedTemplateService.init(user.getTemplates());

        user = new User("dperez15", "David", "Perez", "davidperez10@yahoo.com",passwordEncoder.encode("pass"), 51,"623 768 654",
                "www.youtube.com", "París", "Diseño de Interiores", "freelance", "description", "Diseñador", free,new Date(163598600), "USER");
        this.setUserImage(user, "/static/app/assets/images/people/110/guy-4.jpg");
        userService.save(user);
        user.getTemplates().add(free);
        activeTemplateService.init(user.getTemplates(), user.getActiveTemplate());
        purchasedTemplateService.init(user.getTemplates());

        user = new User("csanchez85", "Carlos", "Sanchez", "carlossanchez99@oulook.com",passwordEncoder.encode("pass"), 51,"629 798 657",
                "www.bitpanda.com", "Valladolid", "Ingenieria de la Energía", "freelance", "description", "Ingeniero", free,new Date(181598600), "USER");
        this.setUserImage(user, "/static/app/assets/images/people/110/guy-5.jpg");
        userService.save(user);
        user.getTemplates().add(free);
        user.getTemplates().add(premium);
        activeTemplateService.init(user.getTemplates(), user.getActiveTemplate());
        purchasedTemplateService.init(user.getTemplates());

        user = new User("rogonza987", "Rodrigo", "Gonzalez", "rodrigonzalez7@gmail.com",passwordEncoder.encode("pass"), 51,"604 347 927",
                "www.telegram.com", "Toledo", "Ingeniería Medioambiental", "freelance", "", "Ingeniero", free,new Date(167598600), "USER");
        this.setUserImage(user, "/static/app/assets/images/people/110/guy-6.jpg");
        userService.save(user);
        user.getTemplates().add(free);
        user.getTemplates().add(premium);
        activeTemplateService.init(user.getTemplates(), user.getActiveTemplate());
        purchasedTemplateService.init(user.getTemplates());

        user = new User("anitarom56", "Ana", "Romero", "anaromeroest79@gmail.com",passwordEncoder.encode("pass"), 51,"690 216 237",
                "www.instagram.com", "Cáceres", "Ingenieria Electrica", "freelance", "description", "Ingeniero", free,new Date(169598600), "USER");
        this.setUserImage(user, "/static/app/assets/images/people/110/woman-1.jpg");
        userService.save(user);
        user.getTemplates().add(free);
        user.getTemplates().add(premium);
        activeTemplateService.init(user.getTemplates(), user.getActiveTemplate());
        purchasedTemplateService.init(user.getTemplates());

        user = new User("siiil99", "Silvia", "Ballesteros", "silviaballesteros98@outlook.com",passwordEncoder.encode("pass"), 51,"639 289 038",
                "www.tuenti.com", "Badajoz", "Fundamentos de la fotografia", "freelance", "description", "Fotografo", free,new Date(161298600), "USER");
        this.setUserImage(user, "/static/app/assets/images/people/110/woman-2.jpg");
        userService.save(user);
        user.getTemplates().add(free);
        activeTemplateService.init(user.getTemplates(), user.getActiveTemplate());
        purchasedTemplateService.init(user.getTemplates());

        user = new User("lauradiaz67", "Laura", "Díaz", "lauradiezmad20@telefonica.net",passwordEncoder.encode("pass"), 51,"699 236 546",
                "www.facebook.com", "Londres", "Ingeniería del software", "freelance", "description", "Ingeniero", free,new Date(161558600), "USER");
        this.setUserImage(user, "/static/app/assets/images/people/110/woman-3.jpg");
        userService.save(user);
        user.getTemplates().add(free);
        user.getTemplates().add(premium);
        activeTemplateService.init(user.getTemplates(), user.getActiveTemplate());
        purchasedTemplateService.init(user.getTemplates());

        user = new User("bertacabello25", "Berta", "Cabello", "bertacabello50@gmail.com",passwordEncoder.encode("pass"), 51,"634 847 935",
                "www.snapchat.com", "Vigo", "Ingeniería Geologica", "freelance", "description", "Ingeniero", free, new Date(166598600),"USER");
        this.setUserImage(user, "/static/app/assets/images/people/110/woman-4.jpg");
        userService.save(user);
        user.getTemplates().add(free);
        user.getTemplates().add(premium);
        activeTemplateService.init(user.getTemplates(), user.getActiveTemplate());
        purchasedTemplateService.init(user.getTemplates());

        user = new User("crisheredia10", "Cristina", "Heredia", "crisheredia11@hotmail.com",passwordEncoder.encode("pass"), 51,"619 025 549",
                "www.elmundo.com", "A Coruña", "Doble Ingenieria Informatica y Software", "freelance", "description", "Ingeniero", free,new Date(111598600), "USER");
        this.setUserImage(user, "/static/app/assets/images/people/110/woman-5.jpg");
        userService.save(user);
        user.getTemplates().add(free);
        user.getTemplates().add(premium);
        activeTemplateService.init(user.getTemplates(), user.getActiveTemplate());
        purchasedTemplateService.init(user.getTemplates());
    }

    public void setUserImage(User user, String classpathResource) throws IOException {
        Resource image = new ClassPathResource(classpathResource);
        user.setProfilePhoto(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }

    public void setPortfolioItemImage(PortfolioItem pItem, String rutaPrev, String ruta1, String ruta2,String ruta3) throws IOException {
        Resource imagePrev = new ClassPathResource(rutaPrev);
        pItem.setPreviewImg(BlobProxy.generateProxy(imagePrev.getInputStream(), imagePrev.contentLength()));

        Resource image1 = new ClassPathResource(ruta1);
        pItem.setImage1(BlobProxy.generateProxy(image1.getInputStream(), image1.contentLength()));

        Resource image2 = new ClassPathResource(ruta2);
        pItem.setImage2(BlobProxy.generateProxy(image2.getInputStream(), image2.contentLength()));

        Resource image3 = new ClassPathResource(ruta3);
        pItem.setImage3(BlobProxy.generateProxy(image3.getInputStream(), image3.contentLength()));
    }

}


