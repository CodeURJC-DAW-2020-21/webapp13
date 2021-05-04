package es.webapp13.porthub.service;

import es.webapp13.porthub.model.ActiveTemplate;
import es.webapp13.porthub.model.PurchasedTemplate;
import es.webapp13.porthub.model.Template;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ActiveTemplateService {

    private final Map<String, Map<Long, ActiveTemplate>> activeTemplateMap = new HashMap<>();

    /**
     * Initialize users active templates
     *
     * @param templates          List of templates in the app
     * @param userActiveTemplate Current user active template
     */
    public void init(String userId, List<Template> templates, Template userActiveTemplate) {
        Map<Long, ActiveTemplate> userActiveTemplateMap = new HashMap<>();
        for (Template template : templates) {
            long templateId = template.getId();
            String name = template.getName();
            String htmlPath = template.getHtmlPath();
            boolean active = template == userActiveTemplate;
            ActiveTemplate activeTemplate = new ActiveTemplate(templateId, htmlPath, name, active);
            userActiveTemplateMap.put(templateId, activeTemplate);
        }
        this.activeTemplateMap.put(userId, userActiveTemplateMap);
    }

    /**
     * Add new template to the templates map
     *
     * @param template Template to add
     */
    public void add(String userId, Template template) {
        long templateId = template.getId();
        String name = template.getName();
        String htmlPath = template.getHtmlPath();
        ActiveTemplate activeTemplate = new ActiveTemplate(templateId, htmlPath, name, false);
        activeTemplateMap.get(userId).put(templateId, activeTemplate);
    }

    /**
     * Get actives templates of the user
     *
     * @return A collection of active templates
     */
    public Collection<ActiveTemplate> findByUserId(String userId) {
        return activeTemplateMap.get(userId).values();
    }

    /**
     * Change the current active template
     *
     * @param oldId Id of the current template
     * @param newId Id of the new template to be active
     */
    public void update(String userId, long oldId, long newId) {
        ActiveTemplate oldActiveTemplate = activeTemplateMap.get(userId).get(oldId);
        oldActiveTemplate.setActive(false);
        ActiveTemplate newActiveTemplate = activeTemplateMap.get(userId).get(newId);
        newActiveTemplate.setActive(true);
    }
}
