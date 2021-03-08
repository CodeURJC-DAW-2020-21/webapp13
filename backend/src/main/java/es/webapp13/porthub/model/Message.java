package es.webapp13.porthub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JsonIgnore
    private User transmitterId;

    @ManyToOne
    @JsonIgnore
    private User receiverId;

    private String text;

    private Date send_date;

    // Constructor necesario para la carga desde BBDD
    protected Message() {}

    public Message(User transmitterId, User receiverId, String text, Date send_date) {
        this.transmitterId = transmitterId;
        this.receiverId = receiverId;
        this.text = text;
        this.send_date = send_date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getTransmitterId() {
        return transmitterId;
    }

    public void setTransmitterId(User transmitterId) {
        this.transmitterId = transmitterId;
    }

    public User getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(User receiverId) {
        this.receiverId = receiverId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getSend_date() {
        return send_date;
    }

    public void setSend_date(Date send_date) {
        this.send_date = send_date;
    }
}
