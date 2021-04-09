package es.webapp13.porthub.model;

import lombok.Data;

@Data
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

}
