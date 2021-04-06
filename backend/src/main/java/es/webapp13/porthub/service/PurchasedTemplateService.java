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
    public void create(List<Template> purchasedTemplates) {
        purchasedTemplateMap = new HashMap<>();
        for (Template template : templateService.findAll()) {
            long id = template.getId();
            setInfo(id, template);
        }
        for (Template template : purchasedTemplates) {
            long id = template.getId();
            PurchasedTemplate purchasedTemplate = purchasedTemplateMap.get(id);
            purchasedTemplate.setPurchased(true);
        }
    }

    /**
     * Add a template that has been purchased by the user
     *
     * @param id Id of the template
     */
    public void add(long id) {
        Template template = templateService.findById(id).orElseThrow();
        setInfo(id, template);
    }

    /**
     * Set info about a purchased template
     * @param id Id of the template
     * @param template A template with the info to be set
     */
    private void setInfo(long id, Template template) {
        String name = template.getName();
        String htmlPath = template.getHtmlPath();
        String description = template.getDescription();
        int price = template.getPrice();
        boolean isFree = template.isFree();
        PurchasedTemplate purchasedTemplate = new PurchasedTemplate(id, htmlPath, name, false, price, isFree, description);
        purchasedTemplateMap.put(id, purchasedTemplate);
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
     * Get the purchased templates list
     *
     * @return List with the purchased templates
     */
    public Collection<PurchasedTemplate> findAll() {
        return purchasedTemplateMap.values();
    }

    /**
     * Get a purchased template by a given id
     *
     * @param id Template id
     * @return A purchased template
     */
    public PurchasedTemplate findPurchased(long id) {
        return purchasedTemplateMap.get(id);
    }


}
