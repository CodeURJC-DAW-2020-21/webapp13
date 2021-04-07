package es.webapp13.porthub.api.user;

import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.SearchService;
import es.webapp13.porthub.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;


@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final SearchService searchService;

    private final ModelMapper modelMapper;

    private final UserService userService;

    public UserRestController(UserService userService, ModelMapper modelMapper, SearchService searchService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.searchService = searchService;
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

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> getImage(@PathVariable String id) throws SQLException {

        Optional<User> user = userService.findById(id);

        if (user.isPresent()) {
            int profilePhotoLength = (int) user.get().getProfilePhoto().length();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(new ByteArrayResource(user.get().getProfilePhoto().getBytes(1, profilePhotoLength)));
        } else
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
    public ResponseEntity<User> postUsers(@ModelAttribute UserDTO userDTO) throws IOException, SQLException {
        User user = modelMapper.map(userDTO, User.class);
        userService.create(user);
        if (userDTO.getProfilePhoto() != null)
            userService.updateProfilePhoto(user, userDTO.getProfilePhoto());


        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable String id) {
        Optional<User> optionalUser = userService.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userService.delete(user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> putUser(@PathVariable String id, @ModelAttribute UserDTO userDTO) throws SQLException, IOException {
        Optional<User> optionalUser = userService.findById(id);
        User user = modelMapper.map(userDTO, User.class);

        if (optionalUser.isPresent()) {
            userService.update(user, id, userDTO.getProfilePhoto());
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
