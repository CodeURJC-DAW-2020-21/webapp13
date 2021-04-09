package es.webapp13.porthub.service;

import es.webapp13.porthub.model.ActiveTemplate;
import es.webapp13.porthub.model.Template;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ActiveTemplateService {

    private Map<Long, ActiveTemplate> activeTemplateMap;

    /**
     * Initialize users active templates
     *
     * @param templates          List of templates in the app
     * @param userActiveTemplate Current user active template
     */
    public void init(List<Template> templates, Template userActiveTemplate) {
        activeTemplateMap = new HashMap<>();
        for (Template template : templates) {
            long id = template.getId();
            String name = template.getName();
            String htmlPath = template.getHtmlPath();
            boolean active = template == userActiveTemplate;
            ActiveTemplate activeTemplate = new ActiveTemplate(id, htmlPath, name, active);
            activeTemplateMap.put(id, activeTemplate);
        }
    }

    /**
     * Add new template to the templates map
     *
     * @param template Template to add
     */
    public void add(Template template) {
        long id = template.getId();
        String name = template.getName();
        String htmlPath = template.getHtmlPath();
        ActiveTemplate activeTemplate = new ActiveTemplate(id, htmlPath, name, false);
        activeTemplateMap.put(id, activeTemplate);
    }

    /**
     * Get actives templates of the user
     *
     * @return A collection of active templates
     */
    public Collection<ActiveTemplate> findAll() {
        return activeTemplateMap.values();
    }

    /**
     * Change the current active template
     *
     * @param oldId Id of the current template
     * @param newId Id of the new template to be active
     */
    public void update(long oldId, long newId) {
        ActiveTemplate oldActiveTemplate = activeTemplateMap.get(oldId);
        oldActiveTemplate.setActive(false);
        ActiveTemplate newActiveTemplate = activeTemplateMap.get(newId);
        newActiveTemplate.setActive(true);
    }
}
