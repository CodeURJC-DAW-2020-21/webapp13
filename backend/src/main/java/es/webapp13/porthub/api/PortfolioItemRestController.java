package es.webapp13.porthub.api;

import es.webapp13.porthub.model.PortfolioItem;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.PortfolioItemService;
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
@RequestMapping("/api/portfolioItems")
public class PortfolioItemRestController {
    private final PortfolioItemService portfolioItemService;

    public PortfolioItemRestController(PortfolioItemService portfolioItemService) {
        this.portfolioItemService = portfolioItemService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Page<PortfolioItem>> getPortfolioItems(Pageable pageable, @PathVariable String userId) {

        Page<PortfolioItem> portfolioItems = portfolioItemService.findPortfolioItems(userId, pageable);

        if (!portfolioItems.isEmpty())
            return ResponseEntity.ok(portfolioItems);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{userId}/{portfolioItemId}")
    public ResponseEntity<PortfolioItem> getPortfolioItem(@PathVariable String userId, @PathVariable long portfolioItemId) {

        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(portfolioItemId);

        return portfolioItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping("/{userId}")
    public ResponseEntity<PortfolioItem> postPortfolioItems(@PathVariable String userId, @RequestBody PortfolioItem portfolioItem) throws IOException {
        portfolioItemService.addPortfolioItem(userId, portfolioItem);

        URI location = fromCurrentRequest().path("/{userId}").buildAndExpand(portfolioItem.getId()).toUri();

        return ResponseEntity.created(location).body(portfolioItem);
    }

    @DeleteMapping("/{userId}/{portfolioItemId}")
    public ResponseEntity<User> deleteUser(@PathVariable String userId, @PathVariable long portfolioItemId) {
        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(portfolioItemId);

        if (portfolioItem.isPresent()) {
            //System.out.println("Antes:"+user.toString());
            portfolioItemService.deletePortfolioItem(userId, portfolioItemId);
            //System.out.println("Despues:"+user.toString());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{portfolioItemId}")
    public ResponseEntity<User> putUser(@PathVariable long portfolioItemId, @RequestBody PortfolioItem newPortfolioItem) throws IOException, SQLException {
        Optional<PortfolioItem> oldPortfolioItem = portfolioItemService.findById(portfolioItemId);

        if (oldPortfolioItem.isPresent()) {
            portfolioItemService.updatePortfolioItem(newPortfolioItem, portfolioItemId);
            //userService.updateUser(newUser,id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
