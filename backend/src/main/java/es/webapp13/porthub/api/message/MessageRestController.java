package es.webapp13.porthub.api.message;


import es.webapp13.porthub.model.Message;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.MessageService;
import es.webapp13.porthub.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.security.Principal;
import java.util.Comparator;
import java.util.LinkedList;
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
     *
     * @param id of the message to get
     * @return the message
     */
    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable long id, HttpServletRequest request) {

        Optional<Message> message = messageService.findById(id);

        Principal principal = request.getUserPrincipal();
        Optional<User> me = userService.findById(principal.getName());

        if (!message.isEmpty()) {
            if (!me.isEmpty() && (me.get().getId().equals(message.get().getSender().getId()) || me.get().getId().equals(message.get().getReceiver().getId())))
                return ResponseEntity.ok(message.get());

            return ResponseEntity.status(403).build();
        } else
            return ResponseEntity.notFound().build();

    }

    /**
     * Get a list of messages between two user
     *
     * @param id1 id of the sender user
     * @param id2 id of the receiver user
     * @return the list of messages
     */
    @GetMapping("/{id1}/{id2}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String id1, @PathVariable String id2, Pageable pageable, HttpServletRequest request) {

        Optional<User> user1 = userService.findById(id1);
        Optional<User> user2 = userService.findById(id2);

        Principal principal = request.getUserPrincipal();
        Optional<User> me = userService.findById(principal.getName());

        if (!user1.isEmpty() && !user2.isEmpty()) {
            if (me.isEmpty() || !user1.get().getId().equals(me.get().getId()))
                return ResponseEntity.status(403).build();
            List<Message> messages = new LinkedList();
            messages.addAll(messageService.findAll(user1.get(), user2.get()));
            messages.addAll(messageService.findAll(user2.get(), user1.get()));
            messages.sort(Comparator.comparing(Message::getSendDate));
            if (!messages.isEmpty())
                return ResponseEntity.ok(messages);
        }
        return ResponseEntity.status(404).build();

    }

    /**
     * @param msgDTO  the message built in JSON
     * @param request information about the user logged in
     * @return the message created or an error 400 if you're not the sender user
     */
    @PostMapping("/")
    public ResponseEntity<Message> postMessageSecuriced(@RequestBody MessageDTO msgDTO, HttpServletRequest request) {

        Optional<User> user1 = userService.findById(msgDTO.getSender());
        Optional<User> user2 = userService.findById(msgDTO.getReceiver());

        Principal principal = request.getUserPrincipal();
        Optional<User> me = userService.findById(principal.getName());

        if (!user1.isEmpty() && !user2.isEmpty() && !me.isEmpty()) {
            if (user1.get().getId().equals(me.get().getId())) {
                Message message = messageService.save(msgDTO);
                URI location = fromCurrentRequest().path("/{id}").buildAndExpand(message.getId()).toUri();
                return ResponseEntity.created(location).body(message);
            } else {
                return ResponseEntity.status(403).build();
            }
        }
        return ResponseEntity.badRequest().build();

    }

}
