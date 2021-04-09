package es.webapp13.porthub.service;

import es.webapp13.porthub.chat.ChatMessage;
import es.webapp13.porthub.chat.ChatService;
import es.webapp13.porthub.model.Message;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.repository.MessageRepository;
import es.webapp13.porthub.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
import java.util.Optional;

@Component
public class MessageService {

    private final MessageRepository messageRepository;

    private final ChatService chatService;

    public MessageService(MessageRepository messageRepository, ChatService chatService) {
        this.messageRepository = messageRepository;
        this.chatService = chatService;
    }

    public Optional<Message> findMessageById(long id){
        return messageRepository.findById(id);
    }

    /**
     * Get all the messages between two users
     *
     * @param sender   The user who sent the messages
     * @param receiver The user who receive the messages
     * @return A list of all messages
     */
    public List<Message> findMessages(User sender, User receiver) {
        return messageRepository.findAllBySenderAndReceiver(sender, receiver);
    }

    public Page<Message> findMessagesPage(User sender, User receiver, Pageable pageable) {
        return messageRepository.findBySenderAndReceiver(sender, receiver, PageRequest.of(pageable.getPageNumber(), 8));
    }

    public void saveMessage(ChatMessage msg) {
        this.chatService.saveMessage(msg);
    }
}
