package es.webapp13.porthub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.io.IOException;
import java.sql.Blob;
import javax.persistence.*;


import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@Data
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

    public User(String id, String name, String surname, String email, String password, long age, String phoneNumber,
                String website, String city, String degree, String freelance, String description, String category, Template activeTemplate, Date date,String... roles) {
        super();
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.city = city;
        this.degree = degree;
        this.freelance = freelance;
        this.description = description;
        this.category = category;
        this.activeTemplate = activeTemplate;
        this.bornDate = date;
        this.roles = List.of(roles);
    }

    public User(String id, String name, String surname, String email, String password, long age, String phoneNumber,
                String website, String city, String degree, String freelance, String description, String category, Template activeTemplate, Date date,List<PortfolioItem> portfolioItemList,String... roles) {
        super();
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.city = city;
        this.degree = degree;
        this.freelance = freelance;
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

    public void addPortfolioItem(PortfolioItem portfolioItem) {
        portfolioItems.add(portfolioItem);
    }

    public void updateProfilePhoto(Blob profilePhoto) throws IOException {
        this.profilePhoto = profilePhoto;
    }
}
