package es.webapp13.porthub.Repository;

import es.webapp13.porthub.model.Template;
import es.webapp13.porthub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findFirstById(String id);
    List<User> findByCategory(String category);
}
