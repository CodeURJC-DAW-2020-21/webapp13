package es.webapp13.porthub.service;


import es.webapp13.porthub.Repository.PortfolioItemRepository;
import es.webapp13.porthub.Repository.TemplateRepository;
import es.webapp13.porthub.model.PortfolioItem;
import es.webapp13.porthub.model.Template;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.Repository.UserRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private PortfolioItemRepository portfolioItemRepository;

    private User activeUser = null;

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    //Create the user in signup
    public void createUser(User user) {

        Template free = templateRepository.findFirstById(1);
        user.getTemplates().add(free);
        Template premium = templateRepository.findFirstById(2);
        user.getTemplates().add(premium);
        user.setActiveTemplate(free);
        java.util.Date currentTime = new java.util.Date();
        long ageMilliseconds = currentTime.getTime() - user.getBornDate().getTime();
        long ageSeconds = ageMilliseconds / 1000;
        long ageMinutes = ageSeconds / 60;
        long ageHours = ageMinutes / 60;
        long ageDays = ageHours / 24;
        long ageYears = ageDays / 365;
        user.setAge(ageYears);
        user.setActiveTemplate(templateRepository.findFirstById(1));
        userRepository.save(user);
    }

    //Profile photo inserted by the user
    public void updateProfilePhoto(User user, MultipartFile imageFile) throws IOException {
        user.setProfilePhoto(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
        userRepository.save(user);
    }

    /**
     * Get all the users in the database (at the moment, later on we should get pageable elements)
     * @return List of users
     */
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findUser(String id) {
        return userRepository.findFirstById(id);
    }

    public String getTemplateHtmlPath(String id) {
        User user = userRepository.findById(id).orElseThrow();
        Template template = user.getActiveTemplate();
        return template.getHtmlPath();
    }

    public List<Template> getTemplates() {
        return activeUser.getTemplates();
    }
}
