package es.webapp13.porthub.service;

import es.webapp13.porthub.repository.UserRepository;
import es.webapp13.porthub.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchService {

    @Autowired
    private UserRepository userRepository;


    public Page<User> getUsersByCategory(String category, Pageable pageable) {
        return userRepository.findByCategory(category, PageRequest.of(pageable.getPageNumber(),8));
    }
}
