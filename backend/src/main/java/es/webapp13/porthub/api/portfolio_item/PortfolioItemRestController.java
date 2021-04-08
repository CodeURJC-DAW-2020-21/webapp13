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

        Page<PortfolioItem> portfolioItems = portfolioItemService.findPortfolioItems(userId, pageable);

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

        portfolioItemService.addPortfolioItem(portfolioItem.getUserId(), portfolioItem);

        if (portfolioItemDTO.getPreviewImg() != null){
            portfolioItemService.updatePreviewImg(portfolioItem, portfolioItemDTO.getPreviewImg());
        }

        if (portfolioItemDTO.getImage1() != null){
            portfolioItemService.updateImg1(portfolioItem, portfolioItemDTO.getImage1());
        }

        if (portfolioItemDTO.getImage2() != null){
            portfolioItemService.updateImg2(portfolioItem, portfolioItemDTO.getImage2());
        }

        if (portfolioItemDTO.getImage3() != null){
            portfolioItemService.updateImg3(portfolioItem, portfolioItemDTO.getImage3());
        }

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(portfolioItem.getId()).toUri();

        return ResponseEntity.created(location).body(portfolioItem);
    }


    @DeleteMapping("/users/{userId}/{portfolioItemId}")
    public ResponseEntity<User> deleteUser(@PathVariable String userId, @PathVariable long portfolioItemId) {
        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(portfolioItemId);

        if (portfolioItem.isPresent()) {
            portfolioItemService.deletePortfolioItem(userId, portfolioItemId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> putUser(@PathVariable long id, @ModelAttribute PortfolioItemDTO portfolioItemDTO) throws IOException, SQLException {
        Optional<PortfolioItem> oldPortfolioItem = portfolioItemService.findById(id);
        PortfolioItem newPortfolioItem = modelMapper.map(portfolioItemDTO, PortfolioItem.class);

        if (oldPortfolioItem.isPresent()) {
            portfolioItemService.updatePortfolioItem(newPortfolioItem, id);
            PortfolioItem portfolioItem = portfolioItemService.findById(id).get();

            if (portfolioItemDTO.getPreviewImg() != null){
                portfolioItemService.updatePreviewImg(portfolioItem, portfolioItemDTO.getPreviewImg());
            }

            if (portfolioItemDTO.getImage1() != null){
                portfolioItemService.updateImg1(portfolioItem, portfolioItemDTO.getImage1());
            }

            if (portfolioItemDTO.getImage2() != null){
                portfolioItemService.updateImg2(portfolioItem, portfolioItemDTO.getImage2());
            }

            if (portfolioItemDTO.getImage3() != null){
                portfolioItemService.updateImg3(portfolioItem, portfolioItemDTO.getImage3());
            }

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}