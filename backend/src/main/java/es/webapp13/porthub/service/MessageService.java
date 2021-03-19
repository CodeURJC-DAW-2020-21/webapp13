package es.webapp13.porthub.service;

import es.webapp13.porthub.model.Message;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> findMessages(User sender, User receiver){
       return messageRepository.findAllBySenderAndReceiver(sender,receiver);
    }
}
