package es.webapp13.porthub.service;


import es.webapp13.porthub.Repository.PortfolioItemRepository;
import es.webapp13.porthub.Repository.TemplateRepository;
import es.webapp13.porthub.model.PortfolioItem;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.Repository.UserRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private PortfolioItemRepository portfolioItemRepository;


    //Create the user in signup
    public void createUser(User user) {
        user.setActiveTemplate(templateRepository.findFirstById(2));


        userRepository.save(user);
    }

    //Profile photo inserted by the user
    public void updateProfilePhoto(User user, MultipartFile imageFile) throws IOException {
        user.setProfilePhoto(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
        userRepository.save(user);

    }

    public User findUser(String id){
        return userRepository.findFirstById(id);
    }

}
