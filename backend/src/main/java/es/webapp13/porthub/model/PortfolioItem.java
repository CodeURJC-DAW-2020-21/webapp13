package es.webapp13.porthub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.sql.Blob;
import java.sql.Date;

@Entity
public class PortfolioItem {

    @Id
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
