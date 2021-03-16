package es.webapp13.porthub.controller;


import es.webapp13.porthub.repository.TemplateRepository;
import es.webapp13.porthub.repository.UserRepository;
import es.webapp13.porthub.model.*;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;

@Controller
public class DataBaseController implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

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



        userRepository.save(new User("id", "name1", "surname", "email@email",passwordEncoder.encode("pass"), "618 99 55 66",
                "www.web.es", "Madrid", "Grado en Ingeniería Informática", "freelance", "description", "job", "Ingeniero", free, "USER"));

        userRepository.save(new User("id2", "name2", "surname2", "email2",passwordEncoder.encode("1234"), "phoneNumber2",
                "website2", "city2", "degree2", "freelance2", "description2", "job2", "Fotografo", premium, "USER", "ADMIN"));

        userRepository.save(new User("id23", "name3", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "job", "Ingeniero", free, "USER"));

        userRepository.save(new User("id3", "name4", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "job", "Ingeniero", free, "USER"));

        userRepository.save(new User("id4", "name5", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "job", "Ingeniero", free, "USER"));

        userRepository.save(new User("id5", "name6", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "job", "Ingeniero", free, "USER"));

        userRepository.save(new User("id6", "name7", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "job", "Ingeniero", free, "USER"));

        userRepository.save(new User("id7", "name8", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "job", "Ingeniero", free, "USER"));

        userRepository.save(new User("id8", "name9", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "job", "Ingeniero", free, "USER"));

        userRepository.save(new User("id9", "name10", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "job", "Ingeniero", free, "USER"));

        userRepository.save(new User("id10", "name11", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "job", "Ingeniero", free, "USER"));

        userRepository.save(new User("id234", "name12", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "job", "Ingeniero", free, "USER"));

        userRepository.save(new User("id35", "name13", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "job", "Ingeniero", free, "USER"));

        userRepository.save(new User("id46", "name14", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "job", "Ingeniero", free, "USER"));

        userRepository.save(new User("id56", "name15", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "job", "Ingeniero", free, "USER"));

        userRepository.save(new User("id66", "name16", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "job", "Ingeniero", free, "USER"));

        userRepository.save(new User("id76", "name17", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "job", "Ingeniero", free, "USER"));

        userRepository.save(new User("id88", "name18", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "job", "Ingeniero", free, "USER"));

        userRepository.save(new User("id99", "name19", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "job", "Ingeniero", free, "USER"));

        userRepository.save(new User("id108", "name20", "surname", "email",passwordEncoder.encode("pass"), "phoneNumber",
                "website", "city", "degree", "freelance", "description", "job", "Ingeniero", free, "USER"));


    }
}


