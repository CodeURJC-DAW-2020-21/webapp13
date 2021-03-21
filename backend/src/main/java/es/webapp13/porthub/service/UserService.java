package es.webapp13.porthub.service;


import es.webapp13.porthub.model.*;
import es.webapp13.porthub.repository.MessageRepository;
import es.webapp13.porthub.repository.PortfolioItemRepository;
import es.webapp13.porthub.repository.UserRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Key;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private PortfolioItemRepository portfolioItemRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PurchasedTemplateService purchasedTemplateService;


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

        List<Message> messages = new LinkedList<>();
        System.out.println("### Lista mensajes creada"+ messages);
        user.setMessages(messages);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        long age = calculateAge(user);
        user.setAge(age);
        user.setActiveTemplate(templateService.findFirstById(1));
        userRepository.save(user);
    }

    public void updateUser(User newUser, String id,MultipartFile profileImg) throws IOException, SQLException {
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

    public void saveChanges(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user){userRepository.delete(user);}

    public long getCountAll(){return userRepository.count();}

    public List<User> findAllUsers(){return userRepository.findAll();}

    /**
     * Update profile photo by a given user
     * @param user
     * @param profileImg
     * @throws IOException
     * @throws SQLException
     */
    private void updateProfilePhoto(User user, MultipartFile profileImg) throws IOException, SQLException {
        if (!profileImg.isEmpty())
            user.setProfilePhoto(BlobProxy.generateProxy(profileImg.getInputStream(), profileImg.getSize()));
        else {

            // Maintain the same image loading it before updating the book
            User dbUser = findById(user.getid()).orElseThrow();
            if (dbUser.getProfilePhoto().length()==0)
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

    public Optional<User> findById(String name) {
        return userRepository.findById(name);
    }


    public List<Message> getMessageList(String id){
        User user = userRepository.findById(id).orElseThrow();
        return user.getMessages();
    }


    public PurchasedTemplate getPopularTemplate(String id){
        User user = userRepository.findById(id).orElseThrow();
        List<User> userList = userRepository.findSimilarUser(user.getCategory());
        Map<Long, Integer> templateMap = new HashMap<>();
        for (User u: userList){
            long templateId = u.getActiveTemplate().getId();
            Integer currentValue = templateMap.get(templateId);
            if (currentValue==null){
                templateMap.put(templateId, 1);
            }else{
                templateMap.put(templateId, currentValue++);
            }
        }
        Integer maxValue = -1;
        Long topId = null;
        for (long k: templateMap.keySet()){
            int currentValue = templateMap.get(k);
            if (currentValue>maxValue){
                maxValue = currentValue;
                topId = k;
            }
        }
        return purchasedTemplateService.getPurchased(topId);
    }

    public Set<String> findChats(User user) {
        List<String> SentMessagesId = messageRepository.findSentChats(user.getid());
        Set<String> chats = new HashSet<>(SentMessagesId);
        List<String> ReceivedMessagesId = messageRepository.findReceivedChats(user.getid());
        chats.addAll(ReceivedMessagesId);
        return chats;
    }

    public void updatePassword(User user, String newPassword){
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
