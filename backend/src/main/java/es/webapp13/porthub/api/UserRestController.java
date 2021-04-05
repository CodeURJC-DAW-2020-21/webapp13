package es.webapp13.porthub.api;

import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<Page<User>> getUsers(Pageable pageable) {

        Page<User> users = userService.findPageUsers(pageable);

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

    @PostMapping("/")
    public ResponseEntity<User> postUsers(@RequestBody User user) throws IOException {
        userService.createUser(user);

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable String id) {
        Optional<User> user = userService.findById(id);

        if (user.isPresent()) {
            //System.out.println("Antes:"+user.toString());
            userService.deleteUser(user.get());
            //System.out.println("Despues:"+user.toString());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> putUser(@PathVariable String id, @RequestBody User newUser) throws IOException, SQLException {
        Optional<User> user = userService.findById(id);

        if (user.isPresent()) {
            user.get().setId(id);
            //userService.updateUser(newUser,id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
