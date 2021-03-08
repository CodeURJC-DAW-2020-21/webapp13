package es.webapp13.porthub.model;

import javax.persistence.Id;
import javax.persistence.OneToOne;

public class UserTemplate {

    @Id
    private User userId;

    @Id
    private Template templateId;

    public UserTemplate() {
    }

    public UserTemplate(User userId, Template template) {
        this.userId = userId;
        this.templateId = template;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Template getTemplate() {
        return templateId;
    }

    public void setTemplate(Template template) {
        this.templateId = template;
    }
}
