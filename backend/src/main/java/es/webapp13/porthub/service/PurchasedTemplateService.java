package es.webapp13.porthub.service;


import es.webapp13.porthub.model.PurchasedTemplate;
import es.webapp13.porthub.model.Template;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PurchasedTemplateService {

    private final TemplateService templateService;

    private final Map<String, Map<Long, PurchasedTemplate>> purchasedTemplateMap = new HashMap<>();

    public PurchasedTemplateService(TemplateService templateService) {
        this.templateService = templateService;
    }

    /**
     * Initialize user purchased templates
     *
     * @param purchasedTemplates List of purchased templates
     */
    public void init(String userId, List<Template> purchasedTemplates) {
        Map<Long, PurchasedTemplate> userPurchasedTemplateMap = new HashMap<>();
        for (Template template : templateService.findAll()) {
            PurchasedTemplate purchasedTemplate = setInfo(template);
            userPurchasedTemplateMap.put(purchasedTemplate.getId(), purchasedTemplate);
        }
        for (Template template : purchasedTemplates) {
            long id = template.getId();
            userPurchasedTemplateMap.get(id).setPurchased(true);
        }
        this.purchasedTemplateMap.put(userId, userPurchasedTemplateMap);
    }

    /**
     * Add a template that has been purchased by the user
     *
     * @param id Id of the template
     */
    public void add(long id) {
        Template template = templateService.findById(id).orElseThrow();
        PurchasedTemplate purchasedTemplate = setInfo(template);
        for (String userId: this.purchasedTemplateMap.keySet()){
            this.purchasedTemplateMap.get(userId).put(purchasedTemplate.getId(), purchasedTemplate);
        }
    }

    /**
     * Set info about a purchased template
     *
     * @param template A template with the info to be set
     */
    private PurchasedTemplate setInfo(Template template) {
        Long id = template.getId();
        String name = template.getName();
        String htmlPath = template.getHtmlPath();
        String description = template.getDescription();
        int price = template.getPrice();
        boolean isFree = template.isFree();
        PurchasedTemplate purchasedTemplate = new PurchasedTemplate(id, htmlPath, name, false, price, isFree, description);
        return purchasedTemplate;
    }

    /**
     * Buy a template
     *
     */
    public void purchase(String userId, long templateId) {
        purchasedTemplateMap.get(userId).get(templateId).setPurchased(true);
    }


    /**
     * Get the purchased templates list
     *
     * @return List with the purchased templates
     */
    public Collection<PurchasedTemplate> findByUserId(String userId) {
        return purchasedTemplateMap.get(userId).values();
    }

    /**
     * Get a purchased template by a given id
     *
     * @return A purchased template
     */
    public PurchasedTemplate findPurchasedTemplate(String userId, long templateId) {
        return purchasedTemplateMap.get(userId).get(templateId);
    }


}
