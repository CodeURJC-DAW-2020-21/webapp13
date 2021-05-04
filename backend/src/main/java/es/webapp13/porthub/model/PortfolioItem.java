package es.webapp13.porthub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Date;

@Data
@Entity
public class PortfolioItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String userId;

    private String name;

    private String description;

    @Lob
    @JsonIgnore
    private Blob previewImage;

    @Lob
    @JsonIgnore
    private Blob image1;

    @Lob
    @JsonIgnore
    private Blob image2;

    @Lob
    @JsonIgnore
    private Blob image3;

    private String category;
    private String client;
    private String url;

    private Date date;

    public PortfolioItem() { }

    public PortfolioItem(String userId, String name, String description, String category, String client, String url, Date date) {
        super();
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.category = category;
        this.client = client;
        this.url = url;
        this.date = date;
    }

    @Override
    public String toString() {
        return "PortfolioItem{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", previewImage=" + previewImage +
                ", image1=" + image1 +
                ", image2=" + image2 +
                ", image3=" + image3 +
                ", category='" + category + '\'' +
                ", client='" + client + '\'' +
                ", url='" + url + '\'' +
                ", date=" + date +
                '}';
    }

}
