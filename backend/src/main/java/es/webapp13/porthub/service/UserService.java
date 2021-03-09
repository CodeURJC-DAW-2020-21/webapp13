package es.webapp13.porthub.service;


import es.webapp13.porthub.Repository.TemplateRepository;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.Repository.UserRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TemplateRepository templateRepository;

    public void createUser(User user, MultipartFile imageFile) throws IOException {
        user.setActiveTemplate(templateRepository.findFirstByTemplateId(2));
        user.setProfilePhoto(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
        userRepository.save(user);
    }


}
