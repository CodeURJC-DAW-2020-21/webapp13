package es.webapp13.porthub.api;

import es.webapp13.porthub.model.Template;
import es.webapp13.porthub.service.TemplateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;


@RestController
@RequestMapping("/api/templates")
public class TemplateRestController {

    private final TemplateService templateService;

    public TemplateRestController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping("/")
    public ResponseEntity<Page<Template>> getTemplates (Pageable pageable){
        Page<Template> templates = templateService.findPageTemplates(pageable);

        if (!templates.isEmpty()){
            return ResponseEntity.ok(templates);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Template> getTemplate (@PathVariable String id){
        Long longId = Long.parseLong(id);
        Template template = templateService.findFirstById(longId);

        if (template != null){
            return ResponseEntity.ok(template);
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/")
    public ResponseEntity<Template> postTemplate (@RequestBody Template template) throws IOException {
        templateService.createTemplate(template);

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(template.getId()).toUri();

        return ResponseEntity.created(location).body(template);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Template> deleteTemplate (@PathVariable String id){
        Long longId = Long.parseLong(id);
        Template template = templateService.findFirstById(longId);

        if (template != null){
            templateService.deleteTemplate(template);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
