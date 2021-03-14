package es.webapp13.porthub.service;


import es.webapp13.porthub.model.ActiveTemplate;
import es.webapp13.porthub.model.PurchasedTemplate;
import es.webapp13.porthub.model.Template;
import es.webapp13.porthub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PurchasedTemplateService {

    @Autowired
    private TemplateService templateService;

    private Map<Long, PurchasedTemplate> purchasedTemplateMap;

    public PurchasedTemplateService() {
    }



    public void init(List<Template> purchasedTemplates) {
        purchasedTemplateMap = new HashMap<>();
        for (Template template: templateService.findAll()){
            long id = template.getId();
            String name = template.getName();
            String htmlPath = template.getHtmlPath();
            String description = template.getDescription();
            int price = template.getPrice();
            boolean isFree = template.isFree();
            boolean purchased = false;
            PurchasedTemplate purchasedTemplate = new PurchasedTemplate(id, htmlPath, name, purchased, price, isFree, description);
            purchasedTemplateMap.put(id, purchasedTemplate);
        }
        for (Template template: purchasedTemplates){
            long id = template.getId();
            PurchasedTemplate purchasedTemplate = purchasedTemplateMap.get(id);
            purchasedTemplate.setPurchased(true);
        }
    }

    public Collection<PurchasedTemplate> getTemplateList(){
        return purchasedTemplateMap.values();
    }

    public void purchase(long id){
        purchasedTemplateMap.get(id).setPurchased(true);
    }

}
