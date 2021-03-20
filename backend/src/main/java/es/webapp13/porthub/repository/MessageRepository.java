package es.webapp13.porthub.repository;

import es.webapp13.porthub.model.Message;
import es.webapp13.porthub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllBySenderAndReceiver(User sender, User receiver);

    @Query("SELECT m.receiver.id FROM Message m WHERE m.sender.id = ?1 GROUP BY m.receiver.id")
    List<String> findChats(String id);
}
