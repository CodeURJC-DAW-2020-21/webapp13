package es.webapp13.porthub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Blob;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import java.util.Date;

@Entity
public class User {

    @Id
    private String username;

    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private Date bornDate;

    @Lob
    @JsonIgnore
    private Blob profilePhoto;

    @OneToOne
    private Template activeTemplate;

    public User() {

    }

    public User(String username, String name, String surname, String email, String password, String phoneNumber, Date bornDate, Blob profilePhoto, Template activeTemplate) {
        super();
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.bornDate = bornDate;
        this.profilePhoto = profilePhoto;
        this.activeTemplate = activeTemplate;
    }


}
