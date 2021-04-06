package es.webapp13.porthub.api;

import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;


@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
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

    @PostMapping("/")
    public ResponseEntity<User> postUsers(@RequestBody User user) throws IOException {
        userService.create(user);

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

    @PutMapping("/{id}")
    public ResponseEntity<User> putUser(@PathVariable String id, @RequestBody User newUser) {
        Optional<User> user = userService.findById(id);

        if (user.isPresent()) {
            user.get().setId(id);
            userService.update(newUser, id);
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<Object> putImage(@PathVariable String id, @RequestParam MultipartFile imageFile) throws IOException, SQLException {
        Optional<User> user = userService.findById(id);

        if (user.isPresent()) {
            URI location = fromCurrentRequest().build().toUri();

            userService.updateProfilePhoto(user.get(), imageFile);
            return ResponseEntity.ok(location);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
