package es.webapp13.porthub.model;

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

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}