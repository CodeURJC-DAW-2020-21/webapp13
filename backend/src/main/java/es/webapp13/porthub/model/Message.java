package es.webapp13.porthub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JsonIgnore
    private User sender;

    @ManyToOne
    @JsonIgnore
    private User receiver;

    private String text;

    private Date sendDate;

    protected Message() {
    }

    public Message(User sender, User receiver, String text, Date sendDate) {
        super();
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.sendDate = sendDate;
    }

}
