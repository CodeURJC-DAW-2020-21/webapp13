package es.webapp13.porthub.api.message;

import lombok.Data;

@Data
public class MessageDTO {

    private String content;

    private String sender;

    private String receiver;

}
