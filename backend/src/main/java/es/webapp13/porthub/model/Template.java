package es.webapp13.porthub.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
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
    public Template(String name, String htmlPath, String description, int price){
        this.name = name;
        this.htmlPath = htmlPath;
        this.description = description;
        this.price = price;
        this.isFree = price == 0;
    }

    public Template(String htmlPath, String name, int price, boolean isFree, String description) {
        this.htmlPath = htmlPath;
        this.name = name;
        this.price = price;
        this.isFree = isFree;
        this.description = description;
    }

}
