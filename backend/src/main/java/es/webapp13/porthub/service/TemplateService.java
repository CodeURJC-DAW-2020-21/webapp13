package es.webapp13.porthub.service;

import es.webapp13.porthub.model.Template;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.repository.TemplateRepository;
import es.webapp13.porthub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TemplateService {
    @Autowired
    private TemplateRepository templateRepository;

    public Template findFirstById(long id){
        return templateRepository.findFirstById(id);
    }

    public Template findFirstByName(String name){
        return templateRepository.findFirstByName(name);
    }

    public List<Template> findAll(){
        return templateRepository.findAll();
    }
}
