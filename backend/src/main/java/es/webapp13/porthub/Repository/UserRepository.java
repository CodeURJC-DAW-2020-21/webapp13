package es.webapp13.porthub.Repository;

import es.webapp13.porthub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
