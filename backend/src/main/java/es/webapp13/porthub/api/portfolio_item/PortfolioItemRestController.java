package es.webapp13.porthub.api.portfolio_item;

import es.webapp13.porthub.model.PortfolioItem;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.service.PortfolioItemService;
import es.webapp13.porthub.service.UserService;
import es.webapp13.porthub.utils.ImageSetter;
import es.webapp13.porthub.utils.ImageUpdater;
import org.hibernate.engine.jdbc.BlobProxy;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
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
    
    private final UserService userService;

    public PortfolioItemRestController(ModelMapper modelMapper, PortfolioItemService portfolioItemService, UserService userService) {
        this.modelMapper = modelMapper;
        this.portfolioItemService = portfolioItemService;
        this.userService = userService;
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

    @GetMapping("/{portfolioItemId}/previewImage")
    public ResponseEntity<Object> getPreviewImage(@PathVariable long portfolioItemId) throws SQLException {

        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(portfolioItemId);

        if (portfolioItem.isPresent()) {
            int profilePhotoLength = (int) portfolioItem.get().getPreviewImg().length();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(new ByteArrayResource(portfolioItem.get().getPreviewImg().getBytes(1, profilePhotoLength)));
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{portfolioItemId}/image1")
    public ResponseEntity<Object> getImage1(@PathVariable long portfolioItemId) throws SQLException {

        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(portfolioItemId);

        if (portfolioItem.isPresent()) {
            int profilePhotoLength = (int) portfolioItem.get().getImage1().length();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(new ByteArrayResource(portfolioItem.get().getImage1().getBytes(1, profilePhotoLength)));
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{portfolioItemId}/image2")
    public ResponseEntity<Object> getImage2(@PathVariable long portfolioItemId) throws SQLException {

        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(portfolioItemId);

        if (portfolioItem.isPresent()) {
            int profilePhotoLength = (int) portfolioItem.get().getImage2().length();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(new ByteArrayResource(portfolioItem.get().getImage2().getBytes(1, profilePhotoLength)));
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{portfolioItemId}/image3")
    public ResponseEntity<Object> getImage3(@PathVariable long portfolioItemId) throws SQLException {

        Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(portfolioItemId);

        if (portfolioItem.isPresent()) {
            int profilePhotoLength = (int) portfolioItem.get().getImage3().length();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(new ByteArrayResource(portfolioItem.get().getImage3().getBytes(1, profilePhotoLength)));
        } else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<PortfolioItem> postPortfolioItem(@PathVariable String userId, @RequestBody PortfolioItemDTO portfolioItemDTO, HttpServletRequest request) throws IOException, SQLException {

        Principal principal = request.getUserPrincipal();
        Optional<User> me = userService.findById(principal.getName());
        
        if (me.isPresent() && userId.contentEquals(me.get().getId())){
            PortfolioItem portfolioItem = modelMapper.map(portfolioItemDTO, PortfolioItem.class);

            portfolioItemService.add(userId, portfolioItem);
            portfolioItemService.save(portfolioItem);
            userService.save(me.get());

            URI location = fromCurrentRequest().path("/{templateId}").buildAndExpand(portfolioItem.getId()).toUri();

            return ResponseEntity.created(location).body(portfolioItem);
        }
        return ResponseEntity.status(403).build();
    }

    @DeleteMapping("/users/{userId}/{portfolioItemId}")
    public ResponseEntity<User> deletePortfolioItem(@PathVariable String userId, @PathVariable long portfolioItemId, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Optional<User> me = userService.findById(principal.getName());
        if (me.isPresent() && userId.contentEquals(me.get().getId())) {

            Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(portfolioItemId);

            if (portfolioItem.isPresent()) {
                portfolioItemService.delete(userId, portfolioItemId);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(403).build();
    }

    @PutMapping("/users/{userId}/{portfolioItemId}")
    public ResponseEntity<PortfolioItem> putPortfolioItem(@PathVariable String userId, @PathVariable long portfolioItemId, @RequestBody PortfolioItemDTO portfolioItemDTO, HttpServletRequest request) throws IOException, SQLException {
        Principal principal = request.getUserPrincipal();
        Optional<User> me = userService.findById(principal.getName());
        if (me.isPresent() && userId.contentEquals(me.get().getId())) {

            Optional<PortfolioItem> oldPortfolioItem = portfolioItemService.findById(portfolioItemId);
            PortfolioItem newPortfolioItem = modelMapper.map(portfolioItemDTO, PortfolioItem.class);

            if (oldPortfolioItem.isPresent()) {
                portfolioItemService.updateText(newPortfolioItem, portfolioItemId);
                portfolioItemService.save(portfolioItemService.findById(portfolioItemId).get());
                userService.save(me.get());

                PortfolioItem portfolioItem = portfolioItemService.findById(portfolioItemId).orElseThrow();

                return ResponseEntity.ok(portfolioItem);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(403).build();
    }

    @PutMapping("/users/{userId}/{portfolioItemId}/previewImage")
    public ResponseEntity<URI> putPreviewImage(@PathVariable String userId, @PathVariable long portfolioItemId, @ModelAttribute PortfolioItemDTO portfolioItemDTO, HttpServletRequest request) throws IOException, SQLException {
        ImageSetter setPreviewImg = (p, i) -> p.setPreviewImg(BlobProxy.generateProxy(i.getInputStream(), i.getSize()));
        ImageUpdater getPreviewImg = (p) -> p.getPreviewImg().length() == 0;
        return putPortfolioItemResponseEntity(userId, portfolioItemId, portfolioItemDTO.getPreviewImg(), request, setPreviewImg, getPreviewImg);
    }

    @PutMapping("/users/{userId}/{portfolioItemId}/image1")
    public ResponseEntity<URI> putImage1(@PathVariable String userId, @PathVariable long portfolioItemId, @ModelAttribute PortfolioItemDTO portfolioItemDTO, HttpServletRequest request) throws IOException, SQLException {
        ImageSetter setImage1 = (p, i) -> p.setImage1(BlobProxy.generateProxy(i.getInputStream(), i.getSize()));
        ImageUpdater getImage1 = (p) -> p.getImage1().length() == 0;
        return putPortfolioItemResponseEntity(userId, portfolioItemId, portfolioItemDTO.getImage1(), request, setImage1, getImage1);
    }

    @PutMapping("/users/{userId}/{portfolioItemId}/image2")
    public ResponseEntity<URI> putImage2(@PathVariable String userId, @PathVariable long portfolioItemId, @ModelAttribute PortfolioItemDTO portfolioItemDTO, HttpServletRequest request) throws IOException, SQLException {
        ImageSetter setImage2 = (p, i) -> p.setImage2(BlobProxy.generateProxy(i.getInputStream(), i.getSize()));
        ImageUpdater getImage2 = (p) -> p.getImage2().length() == 0;
        return putPortfolioItemResponseEntity(userId, portfolioItemId, portfolioItemDTO.getImage2(), request, setImage2, getImage2);
    }

    @PutMapping("/users/{userId}/{portfolioItemId}/image3")
    public ResponseEntity<URI> putImage3(@PathVariable String userId, @PathVariable long portfolioItemId, @ModelAttribute PortfolioItemDTO portfolioItemDTO, HttpServletRequest request) throws IOException, SQLException {
        ImageSetter setImage3 = (p, i) -> p.setImage3(BlobProxy.generateProxy(i.getInputStream(), i.getSize()));
        ImageUpdater getImage3 = (p) -> p.getImage3().length() == 0;
        return putPortfolioItemResponseEntity(userId, portfolioItemId, portfolioItemDTO.getImage3(), request, setImage3, getImage3);
    }

    private ResponseEntity<URI> putPortfolioItemResponseEntity(@PathVariable String userId, @PathVariable long portfolioItemId, MultipartFile image, HttpServletRequest request, ImageSetter imageSetter, ImageUpdater imageUpdater) throws SQLException, IOException {
        Principal principal = request.getUserPrincipal();
        Optional<User> me = userService.findById(principal.getName());
        if (me.isPresent() && userId.contentEquals(me.get().getId())) {
            Optional<PortfolioItem> portfolioItem = portfolioItemService.findById(portfolioItemId);
            if (portfolioItem.isPresent()) {
                portfolioItemService.UpdateImage(portfolioItem.get(), image, imageSetter, imageUpdater);
                portfolioItemService.save(portfolioItem.get());
                URI location = fromCurrentRequest().build().toUri();
                return ResponseEntity.ok(location);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(403).build();
    }

}