package es.webapp13.porthub.repository;

import es.webapp13.porthub.model.Message;
import es.webapp13.porthub.model.Template;
import es.webapp13.porthub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllBySenderAndReceiver(User sender, User receiver);
}
