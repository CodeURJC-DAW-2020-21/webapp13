package es.webapp13.porthub.api;

import es.webapp13.porthub.chat.ChatMessage;
import es.webapp13.porthub.model.Message;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.MessageService;
import es.webapp13.porthub.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;


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
     * @return the list of messages
     */
    @GetMapping("/{id1}/{id2}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String id1, @PathVariable String id2, Pageable pageable) {

        Optional<User> user1 = userService.findById(id1);
        Optional<User> user2 = userService.findById(id2);

        if (!user1.isEmpty() && !user2.isEmpty()) {
            List<Message> messages = messageService.findAll(user1.get(), user2.get());
            if (!messages.isEmpty())
                return ResponseEntity.ok(messages);
        }
        return ResponseEntity.notFound().build();

    }

    /**
     *
     * @param msg the message built in JSON
     * @param request information about the user logged in
     * @return the message created or an error 400 if you're not the sender user
     */
    @PostMapping("/")
    public ResponseEntity<Message> postMessageSecuriced(@RequestBody ChatMessage msg, HttpServletRequest request) {

        Optional<User> user1 = userService.findById(msg.getSender());

        Principal principal = request.getUserPrincipal();
        Optional<User> me = userService.findById(principal.getName());

        if (!user1.isEmpty() && !me.isEmpty()) {
            if (user1.get().getId().equals(me.get().getId())) {
                Message message = messageService.save(msg);
                URI location = fromCurrentRequest().path("/{id}").buildAndExpand(message.getId()).toUri();
                return ResponseEntity.created(location).body(message);

            }
        }
        return ResponseEntity.badRequest().build();
    }


    //This method will be deleted when API security is functional, replaced by method above
    @PostMapping("/{id1}/{id2}")
    public ResponseEntity<ChatMessage> postMessage(@PathVariable String id1, @PathVariable String id2, @RequestBody ChatMessage msg) {

        Optional<User> user1 = userService.findById(msg.getSender());
        Optional<User> user2 = userService.findById(msg.getReceiver());

        if (!user1.isEmpty() && !user2.isEmpty()) {
            if (user1.get().getId().equals(id1) && user2.get().getId().equals(id2)) {
                messageService.save(msg);
                return ResponseEntity.ok(msg);
            }
        }
        return ResponseEntity.notFound().build();

    }
}
