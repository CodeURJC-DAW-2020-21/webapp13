package es.webapp13.porthub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.sql.Blob;
import java.sql.Date;

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
    private Blob previewImg;

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


    public PortfolioItem() {

    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getPreviewImg() {
        return previewImg;
    }

    public void setPreviewImg(Blob previewImg) {
        this.previewImg = previewImg;
    }

    public Blob getImage1() {
        return image1;
    }

    public void setImage1(Blob image1) {
        this.image1 = image1;
    }

    public Blob getImage2() {
        return image2;
    }

    public void setImage2(Blob image2) {
        this.image2 = image2;
    }

    public Blob getImage3() {
        return image3;
    }

    public void setImage3(Blob image3) {
        this.image3 = image3;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PortfolioItem{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", previewImg=" + previewImg +
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
