package es.webapp13.porthub.model;

public class ActiveTemplate {

    private long id;

    private String htmlPath;

    private String name;

    private boolean active;

    public ActiveTemplate(long id, String htmlPath, String name, boolean active) {
        this.id = id;
        this.htmlPath = htmlPath;
        this.name = name;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
