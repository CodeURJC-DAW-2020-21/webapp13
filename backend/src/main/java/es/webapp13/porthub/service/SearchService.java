package es.webapp13.porthub.service;

import es.webapp13.porthub.repository.UserRepository;
import es.webapp13.porthub.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
public class SearchService {

    private final UserRepository userRepository;

    public SearchService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Get users by category
     *
     * @param category A given category
     * @param pageable A pageable param
     * @return A page with users
     */
    public Page<User> getUsersByCategory(String category, Pageable pageable) {
        return userRepository.findByCategory(category, PageRequest.of(pageable.getPageNumber(), 8));
    }

    /**
     * Get users by searching
     *
     * @param search   A string to match
     * @param pageable A pageable param
     * @return A page with users that matches the string
     */
    public Page<User> getUsersBySearch(String search, Pageable pageable) {
        return userRepository.findSearching(search, PageRequest.of(pageable.getPageNumber(), 8));
    }

}
