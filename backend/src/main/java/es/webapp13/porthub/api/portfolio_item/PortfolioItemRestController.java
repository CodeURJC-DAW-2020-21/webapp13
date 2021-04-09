package es.webapp13.porthub.api.portfolio_item;

import es.webapp13.porthub.model.PortfolioItem;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.PortfolioItemService;
import org.modelmapper.ModelMapper;
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

    private final ModelMapper modelMapper;

    private final PortfolioItemService portfolioItemService;

    public PortfolioItemRestController(ModelMapper modelMapper, PortfolioItemService portfolioItemService) {
        this.modelMapper = modelMapper;
        this.portfolioItemService = portfolioItemService;
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Page<PortfolioItem>> getPortfolioItems(Pageable pageable, @PathVariable String userId) {

        Page<PortfolioItem> portfolioItems = portfolioItemService.findPage(userId, pageable);

        if (!portfolioItems.isEmpty())
            return ResponseEntity.ok(portfolioItems);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{portfolioItemId}")
    public ResponseEntity<PortfolioItem> getPortfolioItem(@PathVariable long portfolioItemId) {

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

    @PostMapping("/")
    public ResponseEntity<PortfolioItem> postPortfolioItems(@ModelAttribute PortfolioItemDTO portfolioItemDTO) throws IOException, SQLException {

        PortfolioItem portfolioItem = modelMapper.map(portfolioItemDTO, PortfolioItem.class);

        portfolioItemService.add(portfolioItem.getUserId(), portfolioItem);

        MultipartFile imgP = portfolioItemDTO.getPreviewImg();
        MultipartFile img1 = portfolioItemDTO.getImage1();
        MultipartFile img2 = portfolioItemDTO.getImage2();
        MultipartFile img3 = portfolioItemDTO.getImage3();

        if (imgP != null && img1 != null && img2 != null && img3 != null) {
            portfolioItemService.update(portfolioItem, portfolioItem.getId(), imgP, img1, img2, img3);
        }


        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(portfolioItem.getId()).toUri();

        return ResponseEntity.created(location).body(portfolioItem);
    }


    @DeleteMapping("/users/{userId}/{portfolioItemId}")
    public ResponseEntity<User> deleteUser(@PathVariable String userId, @PathVariable long portfolioItemId) {
        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(portfolioItemId);

        if (portfolioItem.isPresent()) {
            portfolioItemService.delete(userId, portfolioItemId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity<PortfolioItem> putUser(@PathVariable long id, @ModelAttribute PortfolioItemDTO portfolioItemDTO) throws IOException, SQLException {
        Optional<PortfolioItem> oldPortfolioItem = portfolioItemService.findById(id);
        PortfolioItem newPortfolioItem = modelMapper.map(portfolioItemDTO, PortfolioItem.class);

        MultipartFile imgP = portfolioItemDTO.getPreviewImg();
        MultipartFile img1 = portfolioItemDTO.getImage1();
        MultipartFile img2 = portfolioItemDTO.getImage2();
        MultipartFile img3 = portfolioItemDTO.getImage3();

        if (oldPortfolioItem.isPresent()) {
            portfolioItemService.update(newPortfolioItem, id, imgP, img1, img2, img3);
            PortfolioItem portfolioItem = portfolioItemService.findById(id).orElseThrow();

            return ResponseEntity.ok(portfolioItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}