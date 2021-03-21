package es.webapp13.porthub.service;


import es.webapp13.porthub.model.*;
import es.webapp13.porthub.repository.MessageRepository;
import es.webapp13.porthub.repository.UserRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        List<Message> messages = new LinkedList<>();
        System.out.println("### Lista mensajes creada" + messages);
        user.setMessages(messages);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        long age = calculateAge(user);
        user.setAge(age);
        user.setActiveTemplate(templateService.findFirstById(1));
        userRepository.save(user);
    }

    public void updateUser(User newUser, String id, MultipartFile profileImg) throws IOException, SQLException {
        User user = userRepository.findById(id).orElseThrow();
        if (!profileImg.isEmpty()) {
            updateProfilePhoto(user, profileImg);
        }
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());
        user.setEmail(newUser.getEmail());
        user.setPhoneNumber(newUser.getPhoneNumber());
        user.setAge(newUser.getAge());
        user.setCity(newUser.getCity());
        user.setDegree(newUser.getDegree());
        user.setCategory(newUser.getCategory());
        user.setFreelance(newUser.getFreelance());

        userRepository.save(user);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public long getCountAll() {
        return userRepository.count();
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
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

            User dbUser = findById(user.getid()).orElseThrow();
            if (dbUser.getProfilePhoto().length() == 0)
                user.setProfilePhoto(BlobProxy.generateProxy(dbUser.getProfilePhoto().getBinaryStream(), dbUser.getProfilePhoto().length()));
        }
    }

    /**
     * Get all the users in the database (at the moment, later on we should get pageable elements)
     *
     * @return List of users
     */
    public Page<User> findUsers(Pageable pageable) {
        return userRepository.findAll(PageRequest.of(pageable.getPageNumber(), 8));
    }

    public User findUser(String id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User findName(String name) {
        return userRepository.findFirstByName(name);
    }

    public String getTemplateHtmlPath(String id) {
        User user = userRepository.findById(id).orElseThrow();
        Template template = user.getActiveTemplate();
        return template.getHtmlPath();
    }

    public Optional<User> findById(String name) {
        return userRepository.findById(name);
    }

    public List<Message> getMessageList(String id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getMessages();
    }

    public Optional<PurchasedTemplate> getPopularTemplate(String id) {
        User user = userRepository.findById(id).orElseThrow();
        List<User> userList = userRepository.findSimilarUser(user.getCategory());
        Map<Long, Integer> templateMap = new HashMap<>();
        for (User u : userList) {
            long templateId = u.getActiveTemplate().getId();
            Integer currentValue = templateMap.get(templateId);
            if (currentValue == null) {
                templateMap.put(templateId, 1);
            } else {
                templateMap.put(templateId, templateMap.get(templateId) + 1);
            }
        }
        Integer maxValue = -1;
        Long topId = null;
        for (long k : templateMap.keySet()) {
            int currentValue = templateMap.get(k);
            if ((currentValue > maxValue)&&(!purchasedTemplateService.getPurchased(k).isPurchased())) {
                maxValue = currentValue;
                topId = k;
            }
        }
        if (topId==null){
            return Optional.empty();
        }
        return Optional.of(purchasedTemplateService.getPurchased(topId));
    }

    public Set<String> findChats(User user) {
        List<String> SentMessagesId = messageRepository.findSentChats(user.getid());
        Set<String> chats = new HashSet<>(SentMessagesId);
        List<String> ReceivedMessagesId = messageRepository.findReceivedChats(user.getid());
        chats.addAll(ReceivedMessagesId);
        return chats;
    }

    public void updatePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
