package es.webapp13.porthub.api.user;

import es.webapp13.porthub.model.Template;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.SearchService;
import es.webapp13.porthub.service.TemplateService;
import es.webapp13.porthub.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;


@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final SearchService searchService;

    private final ModelMapper modelMapper;

    private final UserService userService;

    private final TemplateService templateService;

    public UserRestController(UserService userService, ModelMapper modelMapper, SearchService searchService, TemplateService templateService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.searchService = searchService;
        this.templateService = templateService;
    }

    @GetMapping("/")
    public ResponseEntity<Page<User>> getUsers(Pageable pageable) {

        Page<User> users = userService.findPage(pageable);

        if (!users.isEmpty())
            return ResponseEntity.ok(users);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {

        Optional<User> user = userService.findById(id);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/me")
    public ResponseEntity<User> getMe(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        if(principal!=null){
            Optional<User> me = userService.findById(principal.getName());
            if(me.isPresent()){
                return ResponseEntity.ok(me.get());
            }
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/me/admin")
    public ResponseEntity<Boolean> isAdmin(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        Optional<User> me = userService.findById(principal.getName());

        if(me.isPresent() && me.get().getRoles().contains("ADMIN"))
            return ResponseEntity.ok(true);
            
        return ResponseEntity.ok(false);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> getImage(@PathVariable String id) throws SQLException {

        Optional<User> user = userService.findById(id);

        if (user.isPresent()) {
            int profilePhotoLength = (int) user.get().getProfilePhoto().length();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(new ByteArrayResource(user.get().getProfilePhoto().getBytes(1, profilePhotoLength)));
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/statistics")
    public ResponseEntity<Collection<Integer>> getUsersStatistics() {

        Collection<Integer> users = userService.findByCreationDate();

        if (!users.isEmpty())
            return ResponseEntity.ok(users);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/engineers")
    public ResponseEntity<Page<User>> getEngineers(Pageable pageable) {

        Page<User> users = searchService.findByCategory("Ingeniero", pageable);

        if (!users.isEmpty())
            return ResponseEntity.ok(users);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/designers")
    public ResponseEntity<Page<User>> getDesigners(Pageable pageable) {

        Page<User> users = searchService.findByCategory("Diseñador", pageable);

        if (!users.isEmpty())
            return ResponseEntity.ok(users);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/photographer")
    public ResponseEntity<Page<User>> getPhotographer(Pageable pageable) {

        Page<User> users = searchService.findByCategory("Fotografo", pageable);

        if (!users.isEmpty())
            return ResponseEntity.ok(users);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/businessman")
    public ResponseEntity<Page<User>> getBusinessman(Pageable pageable) {

        Page<User> users = searchService.findByCategory("Empresario", pageable);

        if (!users.isEmpty())
            return ResponseEntity.ok(users);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<User> postUser(@RequestBody UserDTO userDTO) throws IOException {
        User user = modelMapper.map(userDTO, User.class);
        if (userService.findById(userDTO.getId()).isPresent())
            return ResponseEntity.status(403).build();
        userService.create(user);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable String id,HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Optional<User> me = userService.findById(principal.getName());
        if (me.isPresent() && me.get().getRoles().contains("ADMIN")){
            Optional<User> optionalUser = userService.findById(id);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                userService.delete(user);
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(403).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> putUser(@PathVariable String id, @RequestBody UserDTO userDTO, HttpServletRequest request) throws SQLException, IOException {

        Principal principal = request.getUserPrincipal();
        Optional<User> me = userService.findById(principal.getName());

        Optional<User> optionalUser = userService.findById(id);
        User user = modelMapper.map(userDTO, User.class);

        if (optionalUser.isPresent() && me.isPresent()) {
            if (optionalUser.get().getId().equals(me.get().getId())) {
                userService.update(user, id, userDTO.getProfilePhoto());
                return ResponseEntity.ok(optionalUser.get());
            }
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}/image")
    public ResponseEntity<URI> putUserImage(@PathVariable String id, @ModelAttribute UserDTO userDTO, HttpServletRequest request) throws IOException, SQLException {
        Principal principal = request.getUserPrincipal();
        Optional<User> me = userService.findById(principal.getName());

        Optional<User> user = userService.findById(id);

        if (me.isPresent() && user.isPresent()) {
            if (userDTO.getId().equals(me.get().getId())) {

                if (userDTO.getProfilePhoto() != null) {
                    userService.update(user.get(), user.get().getId(), userDTO.getProfilePhoto());

                    URI location = fromCurrentRequest().build().toUri();
                    return ResponseEntity.ok(location);
                } else
                    return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/activeTemplate/{templateId}")
    public ResponseEntity<Template> putActiveTemplate (@PathVariable String id, @PathVariable int templateId, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> me = userService.findById(principal.getName());

        Optional<User> user = userService.findById(id);
        if (me.isPresent() && user.isPresent()){
            if (user.get().getId().equals(me.get().getId())){
                Optional<Template> template = templateService.findById(templateId);
                if (template.isPresent()){
                    me.get().setActiveTemplate(template.get());
                    userService.save(me.get());
                    return ResponseEntity.ok(template.get());
                }
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(403).build();
    }

    @PutMapping("/{id}/templateList/{templateId}")
    public ResponseEntity<List<Template>> purchaseTemplate (@PathVariable String id, @PathVariable int templateId, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> me = userService.findById(principal.getName());

        Optional<User> user = userService.findById(id);
        if (me.isPresent() && user.isPresent()){
            if (user.get().getId().equals(me.get().getId())){
                Optional<Template> template = templateService.findById(templateId);
                if (template.isPresent()){
                    List<Template> templateList = user.get().getTemplates();
                    templateList.add(template.get());
                    user.get().setTemplates(templateList);
                    return ResponseEntity.ok(templateList);
                }
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(403).build();
    }
}
