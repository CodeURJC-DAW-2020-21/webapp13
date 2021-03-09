package es.webapp13.porthub.Repository;


import es.webapp13.porthub.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, Long> {

    Template findFirstByTemplateId(int templateId);
}
