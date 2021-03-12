package es.webapp13.porthub.repository;


import es.webapp13.porthub.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemplateRepository extends JpaRepository<Template, Long> {

    Template findFirstById(long templateId);
    Template findFirstByName(String name);
    @Override
    List<Template> findAll();
}