package es.webapp13.porthub.service;

import es.webapp13.porthub.model.Template;
import es.webapp13.porthub.repository.TemplateRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TemplateService {

    private final TemplateRepository templateRepository;

    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    /**
     * Find a template by a given id
     *
     * @param id Id of the template
     * @return A template
     */
    public Template findFirstById(long id) {
        return templateRepository.findFirstById(id);
    }

    /**
     * Find a template by a given name
     *
     * @param name Name of the template
     * @return A template
     */
    public Template findFirstByName(String name) {
        return templateRepository.findFirstByName(name);
    }

    /**
     * Find all templates
     *
     * @return A list of templates
     */
    public List<Template> findAll() {
        return templateRepository.findAll();
    }

    /**
     * Number of templates
     *
     * @return Number of templates
     */
    public long getCountAll() {
        return templateRepository.count();
    }

    /**
     * Create a new template
     *
     * @param template A given template
     */
    public void createTemplate(Template template) {
        templateRepository.save(template);
    }

    /**
     * Save a template
     * @param template A given template
     */
    public void save(Template template) {
        templateRepository.save(template);
    }
}
