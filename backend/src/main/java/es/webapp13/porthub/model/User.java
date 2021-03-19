package es.webapp13.porthub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import javax.persistence.*;


import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@DynamicUpdate
public class User {

    @Id
    private String id;

    private String name;
    private String surname;
    private long age;
    private Date bornDate;
    private Date creationDate;

    private String email;
    private String password;

    private String phoneNumber;
    private String website;
    private String city;
    private String category;
    private String degree;
    private String freelance;
    private String description;
    private String job;


    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @Lob
    @JsonIgnore
    private Blob profilePhoto;

    @OneToOne
    private Template activeTemplate;

    @ManyToMany
    private List<Template> templates = new LinkedList<>();

    @OneToMany
    private List<PortfolioItem> portfolioItems;

    @OneToMany(mappedBy = "sender")
    private List<Message> messages = new LinkedList<>();

    public User() {
    }

    public User(String id, String name, String surname, String email, String password, String phoneNumber,
                String website, String city, String degree, String freelance, String description,
                String job, String category, Template activeTemplate, Date date,String... roles) {
        super();
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.city = city;
        this.degree = degree;
        this.freelance = freelance;
        this.job = job;
        this.description = description;
        this.category = category;
        this.activeTemplate = activeTemplate;
        this.bornDate = date;
        this.roles = List.of(roles);
    }

    public User(String id, String name, String surname, String email, String password, String phoneNumber,
                String website, String city, String degree, String freelance, String description,
                String job, String category, Template activeTemplate, Date date,List<PortfolioItem> portfolioItemList,String... roles) {
        super();
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.city = city;
        this.degree = degree;
        this.freelance = freelance;
        this.job = job;
        this.description = description;
        this.category = category;
        this.activeTemplate = activeTemplate;
        this.bornDate = date;
        this.portfolioItems = portfolioItemList;
        this.roles = List.of(roles);
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
                ", website='" + website + '\'' +
                ", city='" + city + '\'' +
                ", degree='" + degree + '\'' +
                ", freelance='" + freelance + '\'' +
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getFreelance() {
        return freelance;
    }

    public void setFreelance(String freelance) {
        this.freelance = freelance;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void addPortfolioItem(PortfolioItem portfolioItem) {
        portfolioItems.add(portfolioItem);
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    public void updateProfilePhoto(Blob profilePhoto) throws IOException {
        this.profilePhoto = profilePhoto;
    }
}
