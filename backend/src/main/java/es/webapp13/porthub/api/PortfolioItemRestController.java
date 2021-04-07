package es.webapp13.porthub.api;

import es.webapp13.porthub.model.PortfolioItem;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.PortfolioItemService;
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

    @GetMapping("/{id}/previewImage")
    public ResponseEntity<Object> getPreviewImage(@PathVariable long id) throws SQLException {

        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(id);

        if (portfolioItem.isPresent()) {
            int profilePhotoLength = (int) portfolioItem.get().getPreviewImg().length();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(new ByteArrayResource(portfolioItem.get().getPreviewImg().getBytes(1, profilePhotoLength)));
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/image1")
    public ResponseEntity<Object> getImage1(@PathVariable long id) throws SQLException {

        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(id);

        if (portfolioItem.isPresent()) {
            int profilePhotoLength = (int) portfolioItem.get().getImage1().length();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(new ByteArrayResource(portfolioItem.get().getImage1().getBytes(1, profilePhotoLength)));
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/image2")
    public ResponseEntity<Object> getImage2(@PathVariable long id) throws SQLException {

        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(id);

        if (portfolioItem.isPresent()) {
            int profilePhotoLength = (int) portfolioItem.get().getImage2().length();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(new ByteArrayResource(portfolioItem.get().getImage2().getBytes(1, profilePhotoLength)));
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/image3")
    public ResponseEntity<Object> getImage3(@PathVariable long id) throws SQLException {

        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(id);

        if (portfolioItem.isPresent()) {
            int profilePhotoLength = (int) portfolioItem.get().getImage3().length();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(new ByteArrayResource(portfolioItem.get().getImage3().getBytes(1, profilePhotoLength)));
        } else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/{userId}")
    public ResponseEntity<PortfolioItem> postPortfolioItems(@PathVariable String userId, @RequestBody PortfolioItem portfolioItem) throws IOException {
        portfolioItemService.addPortfolioItem(userId, portfolioItem);

        URI location = fromCurrentRequest().path("/{userId}").buildAndExpand(portfolioItem.getId()).toUri();

        return ResponseEntity.created(location).body(portfolioItem);
    }

    @PostMapping("/{id}/previewImage")
    public ResponseEntity<Object> postPreviewImage(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException, SQLException {
        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(id);

        if (portfolioItem.isPresent()) {
            URI location = fromCurrentRequest().build().toUri();

            portfolioItemService.setPreviewImg(portfolioItem.get(), imageFile);
            return ResponseEntity.ok(location);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/image1")
    public ResponseEntity<Object> postImage1(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException, SQLException {
        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(id);

        if (portfolioItem.isPresent()) {
            URI location = fromCurrentRequest().build().toUri();

            portfolioItemService.setImg1(portfolioItem.get(), imageFile);
            return ResponseEntity.ok(location);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/image2")
    public ResponseEntity<Object> postImage2(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException, SQLException {
        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(id);

        if (portfolioItem.isPresent()) {
            URI location = fromCurrentRequest().build().toUri();

            portfolioItemService.setImg2(portfolioItem.get(), imageFile);
            return ResponseEntity.ok(location);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/image3")
    public ResponseEntity<Object> postImage3(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException, SQLException {
        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(id);

        if (portfolioItem.isPresent()) {
            URI location = fromCurrentRequest().build().toUri();

            portfolioItemService.setImg3(portfolioItem.get(), imageFile);
            return ResponseEntity.ok(location);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}/{portfolioItemId}")
    public ResponseEntity<User> deleteUser(@PathVariable String userId, @PathVariable long portfolioItemId) {
        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(portfolioItemId);

        if (portfolioItem.isPresent()) {
            portfolioItemService.deletePortfolioItem(userId, portfolioItemId);
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
            //userService.update(newUser,id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/previewImage")
    public ResponseEntity<Object> putPreviewImage(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException, SQLException {
        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(id);

        if (portfolioItem.isPresent()) {
            URI location = fromCurrentRequest().build().toUri();

            portfolioItemService.updatePreviewImg(portfolioItem.get(), imageFile);
            return ResponseEntity.ok(location);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/image1")
    public ResponseEntity<Object> putImage1(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException, SQLException {
        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(id);

        if (portfolioItem.isPresent()) {
            URI location = fromCurrentRequest().build().toUri();

            portfolioItemService.updateImg1(portfolioItem.get(), imageFile);
            return ResponseEntity.ok(location);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/image2")
    public ResponseEntity<Object> putImage2(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException, SQLException {
        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(id);

        if (portfolioItem.isPresent()) {
            URI location = fromCurrentRequest().build().toUri();

            portfolioItemService.updateImg2(portfolioItem.get(), imageFile);
            return ResponseEntity.ok(location);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/image3")
    public ResponseEntity<Object> putImage3(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException, SQLException {
        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(id);

        if (portfolioItem.isPresent()) {
            URI location = fromCurrentRequest().build().toUri();

            portfolioItemService.updateImg3(portfolioItem.get(), imageFile);
            return ResponseEntity.ok(location);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
