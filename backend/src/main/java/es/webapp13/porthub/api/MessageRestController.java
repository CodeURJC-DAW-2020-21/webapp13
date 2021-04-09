package es.webapp13.porthub.api;

import es.webapp13.porthub.chat.ChatMessage;
import es.webapp13.porthub.model.Message;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.MessageService;
import es.webapp13.porthub.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.Optional;


@RestController
@RequestMapping("/api/messages")
public class MessageRestController {

    private final MessageService messageService;

    private final UserService userService;

    public MessageRestController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    /**
     * Get a single message with its id
     * @param id of the message to get
     * @return the message
     */
    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable long id) {

        Optional<Message> message = messageService.findById(id);

        if (!message.isEmpty())
            return ResponseEntity.ok(message.get());
        else
            return ResponseEntity.notFound().build();

    }

    /**
     * Get a list of messages between two user
     * @param id1 id of the sender user
     * @param id2 id of the receiver user
     * @return
     */
    @GetMapping("/{id1}/{id2}")
    public ResponseEntity<Page<Message>> getMessages(@PathVariable String id1, @PathVariable String id2, Pageable pageable) {

        Optional<User> user1 = userService.findById(id1);
        Optional<User> user2 = userService.findById(id2);

        if (!user1.isEmpty() && !user2.isEmpty()) {
            Page<Message> messages = messageService.findPage(user1.get(), user2.get(), pageable);
            if (!messages.isEmpty())
                return ResponseEntity.ok(messages);
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping("/{id1}/{id2}")
    public ResponseEntity<ChatMessage> postMessage(@PathVariable String id1, @PathVariable String id2, @ModelAttribute ChatMessage msg) {

        Optional<User> user1 = userService.findById(HtmlUtils.htmlEscape(msg.getSender()));
        Optional<User> user2 = userService.findById(HtmlUtils.htmlEscape(msg.getReceiver()));

        if(!user1.isEmpty() && !user2.isEmpty()){
            if (user1.get().getId() == id1 && user2.get().getId() == id2) {
                messageService.saveMessage(msg);
                return ResponseEntity.ok(msg);
            }
        }
        return ResponseEntity.notFound().build();

    }
}
