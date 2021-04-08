package es.webapp13.porthub.api.template;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemplateDTO {

    private long id;

    private String htmlPath;

    private String name;

    private int price;

    private boolean isFree;

    private String description;
}
