package es.webapp13.porthub.repository;

import es.webapp13.porthub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    User findFirstById(String id);

    List<User> findByCategory(String category);

    //List<User> findAllByOrderById();

    Optional<User> findById(String name);

    User findFirstByName(String name);

}
