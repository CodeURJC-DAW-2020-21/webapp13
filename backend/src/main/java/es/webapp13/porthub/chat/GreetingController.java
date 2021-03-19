package es.webapp13.porthub.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {


    @MessageMapping("/chat")
    @SendTo("/topic/greetings")
    public Message greeting(Message message) throws Exception {
        return new Message(HtmlUtils.htmlEscape(message.getContent()), HtmlUtils.htmlEscape(message.getSender()));
    }

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }

}
