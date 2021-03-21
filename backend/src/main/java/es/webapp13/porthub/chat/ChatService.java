package es.webapp13.porthub.chat;

import es.webapp13.porthub.model.Message;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.repository.MessageRepository;
import es.webapp13.porthub.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

@Component
public class ChatService {

    private final UserRepository userRepository;

    private final MessageRepository messageRepository;

    public ChatService(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }


    public void saveMessage(ChatMessage msg) {
        User sender = userRepository.findById(HtmlUtils.htmlEscape(msg.getSender())).orElseThrow();
        User receiver = userRepository.findById(HtmlUtils.htmlEscape(msg.getReceiver())).orElseThrow();
        Message message = new Message(sender, receiver, HtmlUtils.htmlEscape(msg.getContent()), new java.util.Date());

        messageRepository.save(message);
        userRepository.save(receiver);
        userRepository.save(sender);
    }
}
