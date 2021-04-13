package es.webapp13.porthub.api.template;

import es.webapp13.porthub.model.Template;
import es.webapp13.porthub.service.TemplateService;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    public TemplateRestController(TemplateService templateService, ModelMapper modelMapper) {
        this.templateService = templateService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ResponseEntity<Page<Template>> getTemplates (Pageable pageable){
        Page<Template> templates = templateService.findPage(pageable);

        if (!templates.isEmpty()){
            return ResponseEntity.ok(templates);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Template> getTemplate (@PathVariable long id){
        Optional<Template> template = templateService.findById(id);
        return template.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Template> postTemplate (@RequestBody TemplateDTO templateDTO) throws IOException {
        Template template = modelMapper.map(templateDTO, Template.class);
        templateService.create(template);

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(template.getId()).toUri();

        return ResponseEntity.created(location).body(template);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Template> deleteTemplate (@PathVariable long id){
        Optional<Template> optionalTemplate = templateService.findById(id);

        if (optionalTemplate.isPresent()){
            Template template = optionalTemplate.get();
            templateService.delete(template);
            return ResponseEntity.ok(template);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
