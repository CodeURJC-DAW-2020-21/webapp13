package es.webapp13.porthub.service;


import es.webapp13.porthub.model.Message;
import es.webapp13.porthub.model.PurchasedTemplate;
import es.webapp13.porthub.model.Template;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.repository.MessageRepository;
import es.webapp13.porthub.repository.UserRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;


@Component
public class UserService {

    private final UserRepository userRepository;

    private final MessageRepository messageRepository;

    private final TemplateService templateService;

    private final PasswordEncoder passwordEncoder;

    private final PurchasedTemplateService purchasedTemplateService;

    public UserService(UserRepository userRepository, MessageRepository messageRepository, TemplateService templateService, PasswordEncoder passwordEncoder, PurchasedTemplateService purchasedTemplateService) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.templateService = templateService;
        this.passwordEncoder = passwordEncoder;
        this.purchasedTemplateService = purchasedTemplateService;
    }

    /**
     * Creates, configure and add a new user to the database
     *
     * @param user User received from a form
     */
    public void create(User user) throws IOException {
        Template free = templateService.findById(1).orElseThrow();
        user.getTemplates().add(free);
        user.setActiveTemplate(free);

        List<String> roles = new LinkedList<>();
        roles.add("USER");
        user.setRoles(roles);

        List<Message> messages = new LinkedList<>();
        user.setMessages(messages);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        long age = calculateAge(user);
        user.setAge(age);

        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        user.setCreationDate(date);

        Resource image = new ClassPathResource("/static/app/assets/images/default-profile.jpg");
        user.setProfilePhoto(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
        userRepository.save(user);
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
        return ageDays / 365;
    }

    /**
     * Save an user in the database
     *
     * @param user A given user to be saved
     */
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * Get all users stored in the database
     *
     * @return A collection of users
     */
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Get a page of users stored in database
     *
     * @return Page of users
     */
    public Page<User> findPage(Pageable pageable) {
        return userRepository.findAll(PageRequest.of(pageable.getPageNumber(), 8));
    }

    /**
     * Get an optional
     *
     * @param name A given name
     * @return An optional data type
     */
    public Optional<User> findById(String name) {
        return userRepository.findById(name);
    }

    /**
     * Get template path
     *
     * @param id A given id
     * @return A path
     */
    public String findTemplateHtmlPath(String id) {
        User user = userRepository.findById(id).orElseThrow();
        Template template = user.getActiveTemplate();
        return template.getHtmlPath();
    }

    /**
     * Get chats by a given user
     *
     * @param user A given user
     * @return A set with all the ids od the users
     */
    public Set<String> findChats(User user) {
        List<String> SentMessagesId = messageRepository.findSentChats(user.getId());
        Set<String> chats = new HashSet<>(SentMessagesId);
        List<String> ReceivedMessagesId = messageRepository.findReceivedChats(user.getId());
        chats.addAll(ReceivedMessagesId);
        return chats;
    }

    /**
     * Get a popular template
     *
     * @param id A given id
     * @return An optional data type
     */
    public Optional<PurchasedTemplate> findPopularTemplate(String id) {
        User user = userRepository.findById(id).orElseThrow();
        List<User> userList = userRepository.findSimilarUser(user.getCategory());
        Map<Long, Integer> templateMap = new HashMap<>();
        for (User u : userList) {
            long templateId = u.getActiveTemplate().getId();
            templateMap.merge(templateId, 1, Integer::sum);
        }
        int maxValue = -1;
        Long topId = null;
        for (long k : templateMap.keySet()) {
            int currentValue = templateMap.get(k);
            if ((currentValue > maxValue) && (!purchasedTemplateService.findById(k).isPurchased())) {
                maxValue = currentValue;
                topId = k;
            }
        }
        if (topId == null) {
            return Optional.empty();
        }
        return Optional.of(purchasedTemplateService.findById(topId));
    }

    /**
     * Get the number of users in the database
     *
     * @return A long number
     */
    public long countAll() {
        return userRepository.count();
    }

    /**
     * Update user info by given parameters
     *
     * @param newUser    The new info
     * @param id         The id of the user
     * @param profileImg The profile photo to be updated
     * @throws IOException  When no photo is present
     * @throws SQLException When updateProfilePhoto() has problems
     */
    public void update(User newUser, String id, MultipartFile profileImg) throws IOException, SQLException {
        User user = userRepository.findById(id).orElseThrow();
        if (profileImg != null) {
            updateProfilePhoto(user, profileImg);
        }
        updateInfo(newUser, user);
    }

    /**
     * Update info
     *
     * @param newUser New info to update the user
     * @param user    User to be updated
     */
    private void updateInfo(User newUser, User user) {
        if (newUser.getName() != null){
            user.setName(newUser.getName());
        }
        if (newUser.getSurname() != null){
            user.setSurname(newUser.getSurname());
        }
        if (newUser.getEmail() != null){
            user.setEmail(newUser.getEmail());
        }
        if (newUser.getPhoneNumber() != null){
            user.setPhoneNumber(newUser.getPhoneNumber());
        }
        if (newUser.getPhoneNumber() != null){
        }
        if (newUser.getCity() != null){
            user.setCity(newUser.getCity());
        }
        if (newUser.getDegree() != null){
            user.setDegree(newUser.getDegree());
        }
        if (newUser.getCategory() != null){
            user.setCategory(newUser.getCategory());
        }
        if (newUser.getFreelance() != null){
            user.setFreelance(newUser.getFreelance());
        }
        if (newUser.getBornDate() != null){
            user.setBornDate(newUser.getBornDate());
            user.setAge(calculateAge(newUser));
        }
        if (newUser.getWebsite() != null){
            user.setWebsite(newUser.getWebsite());
        }
        if (newUser.getDescription() != null){
            user.setDescription(newUser.getDescription());
        }
        if (newUser.getAge() != 0){
            user.setAge(newUser.getAge());
        }
        if (newUser.getPassword() != null){
            user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        }
        userRepository.save(user);
    }

    /**
     * Update profile photo by a given user
     *
     * @param user       User to update his profile photo
     * @param profileImg Image to set
     * @throws IOException  Not found input image
     * @throws SQLException Not found in DB
     */
    private void updateProfilePhoto(User user, MultipartFile profileImg) throws IOException, SQLException {
        if (!profileImg.isEmpty())
            user.setProfilePhoto(BlobProxy.generateProxy(profileImg.getInputStream(), profileImg.getSize()));
        else {

            User dbUser = findById(user.getId()).orElseThrow();
            if (dbUser.getProfilePhoto().length() == 0)
                user.setProfilePhoto(BlobProxy.generateProxy(dbUser.getProfilePhoto().getBinaryStream(), dbUser.getProfilePhoto().length()));
        }
    }

    /**
     * Update password by a given user
     *
     * @param user        A given user
     * @param newPassword The new password
     */
    public void updatePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }


    /**
     * Delete an user from the database
     *
     * @param user A given user to be deleted
     */
    public void delete(User user) {
        userRepository.delete(user);
    }

    public void setCreationDateToUser(User user){
        Calendar calendar = Calendar.getInstance();
        Date creationDate = calendar.getTime();
        user.setCreationDate((java.sql.Date) creationDate);
    }

    /**
     * Find all the users by creation date
     * @return A collection of the users per month
     */
    public Collection<Integer> findByCreationDate(){
        List<Integer> monthList = new LinkedList<>();
        for (int i = 1; i < 13; i++) {
            monthList.add(userRepository.countAllByCreationDate_Month(i, 2021));
        }
        return monthList;
    }

}
