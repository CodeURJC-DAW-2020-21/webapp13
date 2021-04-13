package es.webapp13.porthub.service;

import es.webapp13.porthub.api.message.MessageDTO;
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

    private final UserRepository userRepository;

    private final ChatService chatService;

    public MessageService(MessageRepository messageRepository, ChatService chatService, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.chatService = chatService;
        this.userRepository = userRepository;
    }

    /**
     * Save a message in the database
     *
     * @param messageDTO The message in DTO format
     * @return The message created
     */
    public Message save(MessageDTO messageDTO) {
        User sender = userRepository.findById(messageDTO.getSender()).orElseThrow();
        User receiver = userRepository.findById(messageDTO.getReceiver()).orElseThrow();
        Message message = new Message(sender, receiver, HtmlUtils.htmlEscape(messageDTO.getContent()), new java.util.Date());

        messageRepository.save(message);
        userRepository.save(sender);
        userRepository.save(receiver);

        return message;
    }

    /**
     * Find a message by its id
     *
     * @param id Id of the message
     * @return The message by the given id
     */
    public Optional<Message> findById(long id) {
        return messageRepository.findById(id);
    }

    /**
     * Find all the messages between two users
     *
     * @param sender   The user who sent the messages
     * @param receiver The user who receive the messages
     * @return A list of all messages
     */
    public List<Message> findAll(User sender, User receiver) {
        return messageRepository.findAllBySenderAndReceiver(sender, receiver);
    }

    /**
     * Find a page of messages between two users
     *
     * @param sender   The user who sent the messages
     * @param receiver The user who receive the messages
     * @param pageable Param to set the page number
     * @return A page of messages
     */
    public Page<Message> findPage(User sender, User receiver, Pageable pageable) {
        return messageRepository.findBySenderAndReceiver(sender, receiver, PageRequest.of(pageable.getPageNumber(), 8));
    }
}
