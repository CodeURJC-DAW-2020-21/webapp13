package es.webapp13.porthub.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String htmlPath;

    private String name;

    private int price;

    private boolean isFree;

    private String description;

    public Template() {
    }

    public Template(String htmlPath, String name, int price, boolean isFree, String description) {
        this.htmlPath = htmlPath;
        this.name = name;
        this.price = price;
        this.isFree = isFree;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHtmlPath() {
        return htmlPath;
    }

    public void setHtmlPath(String htmlPath) {
        this.htmlPath = htmlPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
