package es.webapp13.porthub.Repository;

import es.webapp13.porthub.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
