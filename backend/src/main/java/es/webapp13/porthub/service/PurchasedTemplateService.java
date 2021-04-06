package es.webapp13.porthub.service;


import es.webapp13.porthub.model.PurchasedTemplate;
import es.webapp13.porthub.model.Template;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PurchasedTemplateService {

    private final TemplateService templateService;

    private Map<Long, PurchasedTemplate> purchasedTemplateMap;

    public PurchasedTemplateService(TemplateService templateService) {
        this.templateService = templateService;
    }

    /**
     * Initialize user purchased templates
     *
     * @param purchasedTemplates List of purchased templates
     */
    public void init(List<Template> purchasedTemplates) {
        purchasedTemplateMap = new HashMap<>();
        for (Template template : templateService.findAll()) {
            long id = template.getId();
            String name = template.getName();
            String htmlPath = template.getHtmlPath();
            String description = template.getDescription();
            int price = template.getPrice();
            boolean isFree = template.isFree();
            PurchasedTemplate purchasedTemplate = new PurchasedTemplate(id, htmlPath, name, false, price, isFree, description);
            purchasedTemplateMap.put(id, purchasedTemplate);
        }
        for (Template template : purchasedTemplates) {
            long id = template.getId();
            PurchasedTemplate purchasedTemplate = purchasedTemplateMap.get(id);
            purchasedTemplate.setPurchased(true);
        }
    }

    public void addPurchasedTemplate(long id){
        Template template = templateService.findById(id).orElseThrow();
        String name = template.getName();
        String htmlPath = template.getHtmlPath();
        String description = template.getDescription();
        int price = template.getPrice();
        boolean isFree = template.isFree();
        PurchasedTemplate purchasedTemplate = new PurchasedTemplate(id, htmlPath, name, false, price, isFree, description);
        purchasedTemplateMap.put(id, purchasedTemplate);
    }

    /**
     * Get the purchased templates list
     *
     * @return List with the purchased templates
     */
    public Collection<PurchasedTemplate> getTemplateList() {
        return purchasedTemplateMap.values();
    }

    /**
     * Buy a template
     *
     * @param id Template id
     */
    public void purchase(long id) {
        purchasedTemplateMap.get(id).setPurchased(true);
    }

    /**
     * Get a purchased template by a given id
     *
     * @param id Template id
     * @return A purchased template
     */
    public PurchasedTemplate getPurchased(long id) {
        return purchasedTemplateMap.get(id);
    }

}
