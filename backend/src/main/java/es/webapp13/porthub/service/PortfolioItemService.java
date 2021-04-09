package es.webapp13.porthub.service;

import es.webapp13.porthub.model.PortfolioItem;
import es.webapp13.porthub.model.User;
import es.webapp13.porthub.repository.PortfolioItemRepository;
import es.webapp13.porthub.repository.UserRepository;
import es.webapp13.porthub.utils.ImageSetter;
import es.webapp13.porthub.utils.ImageUpdater;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class PortfolioItemService {

    private final UserRepository userRepository;

    private final PortfolioItemRepository portfolioItemRepository;


    public PortfolioItemService(UserRepository userRepository, PortfolioItemRepository portfolioItemRepository) {
        this.userRepository = userRepository;
        this.portfolioItemRepository = portfolioItemRepository;
    }

    /**
     * Add portfolio item to a user by a given id
     *
     * @param userId Id of the user
     * @param item   Form with the portfolio item information
     */
    public void create(String userId, PortfolioItem item, MultipartFile previewImg, MultipartFile image1, MultipartFile image2, MultipartFile image3) throws IOException {
        item.setUserId(userId);
        item.setPreviewImg(BlobProxy.generateProxy(previewImg.getInputStream(), previewImg.getSize()));
        item.setImage1(BlobProxy.generateProxy(image1.getInputStream(), image1.getSize()));
        item.setImage2(BlobProxy.generateProxy(image2.getInputStream(), image2.getSize()));
        item.setImage3(BlobProxy.generateProxy(image3.getInputStream(), image3.getSize()));
        portfolioItemRepository.save(item);

        User user = userRepository.findById(userId).orElseThrow();
        user.addPortfolioItem(item);
        userRepository.save(user);
    }

    public void add(String userId, PortfolioItem portfolioItem) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(value -> value.addPortfolioItem(portfolioItem));
    }

    /**
     * Save a portfolio item
     *
     * @param portfolioItem A given portfolio item
     */
    public void save(PortfolioItem portfolioItem) {
        portfolioItemRepository.save(portfolioItem);
    }

    /**
     * Get a page with 3 portfolio items
     *
     * @param userId   Id of the user
     * @param pageable Pageable param
     * @return A page with 3 portfolio items
     */
    public Page<PortfolioItem> findPage(String userId, Pageable pageable) {
        return portfolioItemRepository.findByUserId(userId, PageRequest.of(pageable.getPageNumber(), 3));
    }

    /**
     * Get a portfolio item by a given id
     *
     * @param id Id of the portfolio item
     * @return Optional value
     */
    public Optional<PortfolioItem> findById(long id) {
        return portfolioItemRepository.findById(id);
    }

    /**
     * Update a portfolio item fields
     *
     * @param item form with the changes to the portfolio item
     * @param id   id of the portfolio item
     */
    public void update(PortfolioItem item, long id, MultipartFile previewImg, MultipartFile image1, MultipartFile image2, MultipartFile image3) throws IOException, SQLException {
        PortfolioItem portfolioItem = portfolioItemRepository.findById(id).orElseThrow();

        ImageSetter setPreviewImg = (p, i) -> p.setPreviewImg(BlobProxy.generateProxy(i.getInputStream(), i.getSize()));
        ImageSetter setImg1 = (p, i) -> p.setImage1(BlobProxy.generateProxy(i.getInputStream(), i.getSize()));
        ImageSetter setImg2 = (p, i) -> p.setImage2(BlobProxy.generateProxy(i.getInputStream(), i.getSize()));
        ImageSetter setImg3 = (p, i) -> p.setImage3(BlobProxy.generateProxy(i.getInputStream(), i.getSize()));

        ImageUpdater getPreviewImg = (p) -> p.getPreviewImg().length() == 0;
        ImageUpdater getImg1 = (p) -> p.getImage1().length() == 0;
        ImageUpdater getImg2 = (p) -> p.getImage2().length() == 0;
        ImageUpdater getImg3 = (p) -> p.getImage3().length() == 0;

        UpdateImage(portfolioItem, previewImg, setPreviewImg, getPreviewImg);
        UpdateImage(portfolioItem, image1, setImg1, getImg1);
        UpdateImage(portfolioItem, image2, setImg2, getImg2);
        UpdateImage(portfolioItem, image3, setImg3, getImg3);

        portfolioItem.setName(item.getName());
        portfolioItem.setCategory(item.getCategory());
        portfolioItem.setClient(item.getClient());
        portfolioItem.setDate(item.getDate());
        portfolioItem.setUrl(item.getUrl());
        portfolioItem.setDescription(item.getDescription());
        portfolioItemRepository.save(portfolioItem);
    }

    /**
     * Generic method to update portfolio items images
     *
     * @param item   Portfolio item to be updated
     * @param img    Image to be updated
     * @param setImg Lambda expression to set image
     * @param getImg Lambda expression to get image
     * @throws SQLException Image not found in database
     * @throws IOException  No image at input
     */
    private void UpdateImage(PortfolioItem item, MultipartFile img, ImageSetter setImg, ImageUpdater getImg) throws SQLException, IOException {
        if (!img.isEmpty())
            setImg.run(item, img);
        else {
            PortfolioItem dbItem = findById(item.getId()).orElseThrow();
            if (getImg.run(dbItem))
                item.setPreviewImg(BlobProxy.generateProxy(dbItem.getPreviewImg().getBinaryStream(), dbItem.getPreviewImg().length()));
        }
    }

    /**
     * Delete a portfolio item by a given userId and itemId
     *
     * @param userId id of the user
     * @param itemId id of the item
     */
    public void delete(String userId, long itemId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<PortfolioItem> portfolioItemList = user.getPortfolioItems();
        PortfolioItem portfolioItem = portfolioItemRepository.findById(itemId).orElseThrow();

        portfolioItemList.remove(portfolioItem);
        portfolioItemRepository.delete(portfolioItem);
        userRepository.save(user);
    }
}