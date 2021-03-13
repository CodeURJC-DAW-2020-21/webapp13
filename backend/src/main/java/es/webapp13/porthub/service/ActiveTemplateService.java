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

    public ActiveTemplateService() {
    }



    public void init(List<Template> templates, Template userActiveTemplate) {
        activeTemplateMap = new HashMap<>();
        for (Template template: templates){
            long id = template.getId();
            String name = template.getName();
            String htmlPath = template.getHtmlPath();
            boolean active = false;
            if (template == userActiveTemplate){
                active = true;
            }
            ActiveTemplate activeTemplate = new ActiveTemplate(id,htmlPath, name, active);
            activeTemplateMap.put(id, activeTemplate);
        }
    }

    public Collection<ActiveTemplate> getActiveTemplateList(){
        return activeTemplateMap.values();
    }

    public void changeActiveTemplate(long oldId, long newId){
        ActiveTemplate oldActiveTemplate = activeTemplateMap.get(oldId);
        oldActiveTemplate.setActive(false);
        ActiveTemplate newActiveTemplate = activeTemplateMap.get(newId);
        newActiveTemplate.setActive(true);
    }

}
