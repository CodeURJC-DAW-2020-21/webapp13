package es.webapp13.porthub.chat;

import lombok.Data;

@Data
public class ChatMessage {

    private String content;
    private String sender;
    private String receiver;

    public ChatMessage(String content, String sender, String receiver) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

}
