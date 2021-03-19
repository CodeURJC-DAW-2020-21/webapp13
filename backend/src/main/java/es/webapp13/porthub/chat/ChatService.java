package es.webapp13.porthub.chat;

import es.webapp13.porthub.model.Message;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.repository.MessageRepository;
import es.webapp13.porthub.repository.UserRepository;
import es.webapp13.porthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class ChatService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;


    public void saveMessage(ChatMessage msg) {
        User sender = userRepository.findById(HtmlUtils.htmlEscape(msg.getSender())).orElseThrow();
        User receiver = userRepository.findById(HtmlUtils.htmlEscape(msg.getReceiver())).orElseThrow();
        Message message = new Message(sender,receiver,HtmlUtils.htmlEscape(msg.getContent()),new java.util.Date());

        messageRepository.save(message);
        userRepository.save(receiver);
        userRepository.save(sender);
    }
}
