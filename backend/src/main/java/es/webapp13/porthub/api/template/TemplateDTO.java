package es.webapp13.porthub.api.template;

import lombok.Data;

@Data
public class TemplateDTO {

    private long id;

    private String htmlPath;

    private String name;

    private int price;

    private boolean isFree;

    private String description;
}
