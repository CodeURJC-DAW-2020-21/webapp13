package es.webapp13.porthub.service;

import es.webapp13.porthub.model.Template;
import es.webapp13.porthub.repository.TemplateRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TemplateService {

    private final TemplateRepository templateRepository;

    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    /**
     * Create a new template
     *
     * @param template A given template
     */
    public void create(Template template) {
        templateRepository.save(template);
    }

    /**
     * Save a template
     *
     * @param template A given template
     */
    public void save(Template template) {
        templateRepository.save(template);
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
     * Get a page of templates stored in database
     *
     * @return Page of templates
     */
    public Page<Template> findPage(Pageable pageable) {
        return templateRepository.findAll(PageRequest.of(pageable.getPageNumber(), 8));
    }

    /**
     * Find a template by a given id
     *
     * @param id Id of the template
     * @return A template
     */
    public Optional<Template> findById(long id) {
        return templateRepository.findById(id);
    }

    /**
     * Delete an template from the database
     *
     * @param template A given template to be deleted
     */
    public void delete(Template template) {
        templateRepository.delete(template);
    }

}
