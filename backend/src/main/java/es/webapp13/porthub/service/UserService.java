package es.webapp13.porthub.service;


import es.webapp13.porthub.repository.PortfolioItemRepository;
import es.webapp13.porthub.model.Template;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.repository.UserRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private PortfolioItemRepository portfolioItemRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User activeUser = null;


    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    /**
     * Calculate the user age
     *
     * @param user A given user
     * @return The age of the user
     */
    private long calculateAge(User user) {
        java.util.Date currentTime = new java.util.Date();
        long ageMilliseconds = currentTime.getTime() - user.getBornDate().getTime();
        long ageSeconds = ageMilliseconds / 1000;
        long ageMinutes = ageSeconds / 60;
        long ageHours = ageMinutes / 60;
        long ageDays = ageHours / 24;
        long ageYears = ageDays / 365;
        return ageYears;
    }

    /**
     * Creates, configure and add a new user to the database
     *
     * @param user User received from a form
     */
    public void createUser(User user) {
        Template free = templateService.findFirstById(1);
        user.getTemplates().add(free);
        user.setActiveTemplate(free);

        List<String> roles = new LinkedList<>();
        roles.add("USER");
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        long age = calculateAge(user);
        user.setAge(age);
        user.setActiveTemplate(templateService.findFirstById(1));
        userRepository.save(user);
    }

    public void saveChanges(User user) {
        userRepository.save(user);
    }

    /**
     * Update the profile photo of a given user by a given image
     *
     * @param user      A user given
     * @param imageFile A image given
     * @throws IOException
     */
    public void updateProfilePhoto(User user, MultipartFile imageFile) throws IOException {
        user.setProfilePhoto(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
        userRepository.save(user);
    }

    /**
     * Get all the users in the database (at the moment, later on we should get pageable elements)
     *
     * @return List of users
     */
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findUser(String id) {
        return userRepository.findFirstById(id);
    }

    public User findName(String name) {
        return userRepository.findFirstByName(name);
    }

    public String getTemplateHtmlPath(String id) {
        User user = userRepository.findById(id).orElseThrow();
        Template template = user.getActiveTemplate();
        return template.getHtmlPath();
    }

    /**
     * Get a list of templates
     *
     * @return List of templates
     */
    public List<Template> getTemplates() {
        return activeUser.getTemplates();
    }


    public Page<User> findUsersPage(Pageable page) {
        return userRepository.findAll(page);
    }

    public Optional<User> findById(String name) {
        return userRepository.findById(name);
    }
}
