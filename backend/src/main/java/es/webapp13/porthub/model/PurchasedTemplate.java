package es.webapp13.porthub.model;

import lombok.Data;

@Data
public class PurchasedTemplate {

    private long id;

    private String htmlPath;

    private String name;

    private boolean purchased;

    private int price;

    private boolean isFree;

    private String description;

    public PurchasedTemplate(long id, String htmlPath, String name, boolean purchased, int price, boolean isFree, String description) {
        this.id = id;
        this.htmlPath = htmlPath;
        this.name = name;
        this.purchased = purchased;
        this.price = price;
        this.isFree = isFree;
        this.description = description;
    }

}