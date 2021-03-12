package es.webapp13.porthub.service;

import es.webapp13.porthub.Repository.UserRepository;
import es.webapp13.porthub.model.PortfolioItem;
import es.webapp13.porthub.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchService {

    @Autowired
    private UserRepository userRepository;
    
    /**
     * Get all the users in the database by a given category (at the moment, later on we should get pageable elements)
     * @param category Category of a user
     * @return List of users
     */
    public List<User> getUsersByCategory(String category){
        return userRepository.findByCategory(category);
    }
}
