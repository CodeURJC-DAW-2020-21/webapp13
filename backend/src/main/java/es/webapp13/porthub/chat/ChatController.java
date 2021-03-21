package es.webapp13.porthub.chat;

import es.webapp13.porthub.model.Message;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.MessageService;
import es.webapp13.porthub.service.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Controller
public class ChatController {

    private final UserService userService;

    private final ChatService chatService;

    private final MessageService messageService;

    public ChatController(UserService userService, ChatService chatService, MessageService messageService) {
        this.userService = userService;
        this.chatService = chatService;
        this.messageService = messageService;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/greetings")
    public ChatMessage greeting(ChatMessage message) throws Exception {
        chatService.saveMessage(message);
        return new ChatMessage(HtmlUtils.htmlEscape(message.getContent()), HtmlUtils.htmlEscape(message.getSender()), HtmlUtils.htmlEscape(message.getReceiver()));
    }

    @GetMapping("/chat/{id}")
    public String chat(Model model, @PathVariable String id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userService.findUser(principal.getName());
        if (user.getid().equals(id))
            return "error";
        List<Message> messages = new LinkedList<>();
        User u = userService.findUser(id);
        List<Message> messagesSender = messageService.findMessages(user, u);
        List<Message> messagesReceiver = messageService.findMessages(u, user);
        messages.addAll(messagesSender);
        messages.addAll(messagesReceiver);
        messages.sort(Comparator.comparing(Message::getSendDate));

        model.addAttribute("receiver", userService.findUser(id));

        model.addAttribute("messages", messages);
        return "chat";
    }

    @GetMapping("/active_chats")
    public String activeChatLink(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userService.findUser(principal.getName());
        Set<String> userIdList = userService.findChats(user);
        List<User> userList = new LinkedList<>();
        for (String u : userIdList) {
            userList.add(userService.findUser(u));
        }
        model.addAttribute("users", userList);
        model.addAttribute("active_chat", true);
        return "active_chat";
    }

}
