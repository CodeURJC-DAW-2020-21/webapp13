package es.webapp13.porthub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
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

    public PortfolioItem(int id, String userId, String name, String description, Blob previewImg, Blob image1, Blob image2, Blob image3, String category, Date date) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.previewImg = previewImg;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.category = category;
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

    public void setPreviewImg(MultipartFile previewImg) throws IOException {
        this.previewImg = BlobProxy.generateProxy(previewImg.getInputStream(), previewImg.getSize());
    }

    public Blob getImage1() {
        return image1;
    }

    public void setImage1(MultipartFile image1) throws IOException {
        this.image1 = BlobProxy.generateProxy(image1.getInputStream(), image1.getSize());
    }

    public Blob getImage2() {
        return image2;
    }

    public void setImage2(MultipartFile image2) throws IOException {
        this.image2 = BlobProxy.generateProxy(image2.getInputStream(), image2.getSize());
    }

    public Blob getImage3() {
        return image3;
    }

    public void setImage3(MultipartFile image3) throws IOException {
        this.image3 = BlobProxy.generateProxy(image3.getInputStream(), image3.getSize());
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

    public void updatePreviewImg(Blob previewImg) throws IOException {
        this.previewImg = previewImg;
    }

    public void updateImage1(Blob image1) throws IOException {
        this.image1 = image1;
    }

    public void updateImage2(Blob image2) throws IOException {
        this.image2 = image2;
    }

    public void updateImage3(Blob image3) throws IOException {
        this.image3 = image3;
    }

}
