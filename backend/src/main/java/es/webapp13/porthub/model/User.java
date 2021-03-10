package es.webapp13.porthub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Blob;
import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class User {

    @Id
    private String id;

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

    @OneToMany
    private List<Template> templates;

    @OneToMany
    private List<PortfolioItem> portfolioItems;

    @OneToMany(mappedBy = "transmitterId")
    private List<Message> messages;

    public User() {

    }

    public User(String id, String name, String surname) {
        super();
        this.id = id;
        this.name = name;
        this.surname = surname;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bornDate=" + bornDate +
                ", profilePhoto=" + profilePhoto +
                ", activeTemplate=" + activeTemplate +
                ", templates=" + templates +
                ", portfolioItems=" + portfolioItems +
                ", messages=" + messages +
                '}';
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public Blob getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(Blob profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Template getActiveTemplate() {
        return activeTemplate;
    }

    public void setActiveTemplate(Template activeTemplate) {
        this.activeTemplate = activeTemplate;
    }

    public List<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(List<Template> templates) {
        this.templates = templates;
    }

    public List<PortfolioItem> getPortfolioItems() {
        return portfolioItems;
    }

    public void setPortfolioItems(List<PortfolioItem> portfolioItems) {
        this.portfolioItems = portfolioItems;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addPortfolioItem(PortfolioItem portfolioItem){
        portfolioItems.add(portfolioItem);
    }

}
