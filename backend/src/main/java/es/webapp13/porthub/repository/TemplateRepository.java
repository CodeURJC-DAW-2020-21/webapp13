package es.webapp13.porthub.repository;


import es.webapp13.porthub.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TemplateRepository extends JpaRepository<Template, Long> {

    Optional<Template> findById(long id);

    Template findFirstByName(String name);

    List<Template> findAll();

}
