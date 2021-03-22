package es.webapp13.porthub.service;

import es.webapp13.porthub.repository.PortfolioItemRepository;
import es.webapp13.porthub.repository.UserRepository;
import es.webapp13.porthub.model.PortfolioItem;
import es.webapp13.porthub.model.User;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
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
    public void addPortfolioItem(String userId, PortfolioItem item, MultipartFile previewImg, MultipartFile image1, MultipartFile image2, MultipartFile image3) throws IOException {
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

    /**
     * Return a portfolio item by a given user id and portfolio item id
     *
     * @param userId Id of the user
     * @param itemId Id of the portfolio item
     * @return Portfolio item
     */
    public PortfolioItem getPortfolioItem(String userId, long itemId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<PortfolioItem> portfolioItemList = user.getPortfolioItems();
        for (PortfolioItem p : portfolioItemList) {
            if (p.getId() == itemId)
                return p;
        }
        throw new NoSuchElementException("No portfolio item associated to this user id");
    }

    /**
     * Update a portfolio item fields
     *
     * @param item form with the changes to the portfolio item
     * @param id   id of the portfolio item
     */
    public void updatePortfolioItem(PortfolioItem item, long id, MultipartFile previewImg, MultipartFile image1, MultipartFile image2, MultipartFile image3) throws IOException, SQLException {
        PortfolioItem portfolioItem = portfolioItemRepository.findById(id).orElseThrow();
        updatePreviewImg(portfolioItem, previewImg);
        updateImg1(portfolioItem, image1);
        updateImg2(portfolioItem, image2);
        updateImg3(portfolioItem, image3);
        portfolioItem.setName(item.getName());
        portfolioItem.setCategory(item.getCategory());
        portfolioItem.setClient(item.getClient());
        portfolioItem.setDate(item.getDate());
        portfolioItem.setUrl(item.getUrl());
        portfolioItem.setDescription(item.getDescription());
        portfolioItemRepository.save(portfolioItem);
    }

    /**
     * Update Preview image
     *
     * @param item       Portfolio item to update preview image
     * @param previewImg Portfolio preview image
     * @throws IOException  Not found input image
     * @throws SQLException Not found in DB
     */
    private void updatePreviewImg(PortfolioItem item, MultipartFile previewImg) throws IOException, SQLException {
        if (!previewImg.isEmpty())
            item.setPreviewImg(BlobProxy.generateProxy(previewImg.getInputStream(), previewImg.getSize()));
        else {

            // Maintain the same image loading it before updating the book
            PortfolioItem dbItem = findById(item.getId()).orElseThrow();
            if (dbItem.getPreviewImg().length() == 0)
                item.setPreviewImg(BlobProxy.generateProxy(dbItem.getPreviewImg().getBinaryStream(), dbItem.getPreviewImg().length()));
        }
    }

    /**
     * Update image 1
     *
     * @param item Portfolio item to update image
     * @param img  Portfolio image
     * @throws IOException  Not found input image
     * @throws SQLException Not found in DB
     */
    private void updateImg1(PortfolioItem item, MultipartFile img) throws IOException, SQLException {
        if (!img.isEmpty())
            item.setImage1(BlobProxy.generateProxy(img.getInputStream(), img.getSize()));
        else {

            // Maintain the same image loading it before updating the book
            PortfolioItem dbItem = findById(item.getId()).orElseThrow();
            if (dbItem.getImage1().length() == 0)
                item.setImage1(BlobProxy.generateProxy(dbItem.getImage1().getBinaryStream(), dbItem.getImage1().length()));
        }
    }

    /**
     * Update image 2
     *
     * @param item Portfolio item to update image
     * @param img  Portfolio image
     * @throws IOException  Not found input image
     * @throws SQLException Not found in DB
     */
    private void updateImg2(PortfolioItem item, MultipartFile img) throws IOException, SQLException {
        if (!img.isEmpty())
            item.setImage2(BlobProxy.generateProxy(img.getInputStream(), img.getSize()));
        else {

            // Maintain the same image loading it before updating the book
            PortfolioItem dbItem = findById(item.getId()).orElseThrow();
            if (dbItem.getImage2().length() == 0)
                item.setImage2(BlobProxy.generateProxy(dbItem.getImage2().getBinaryStream(), dbItem.getImage2().length()));
        }
    }

    /**
     * Update image 1
     *
     * @param item Portfolio item to update image
     * @param img  Portfolio image
     * @throws IOException  Not found input image
     * @throws SQLException Not found in DB
     */
    private void updateImg3(PortfolioItem item, MultipartFile img) throws IOException, SQLException {
        if (!img.isEmpty())
            item.setImage3(BlobProxy.generateProxy(img.getInputStream(), img.getSize()));
        else {

            // Maintain the same image loading it before updating the book
            PortfolioItem dbItem = findById(item.getId()).orElseThrow();
            if (dbItem.getImage3().length() == 0)
                item.setImage3(BlobProxy.generateProxy(dbItem.getImage3().getBinaryStream(), dbItem.getImage3().length()));
        }
    }

    /**
     * Delete a portfolio item by a given userId and itemId
     *
     * @param userId id of the user
     * @param itemId id of the item
     */
    public void deletePortfolioItem(String userId, long itemId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<PortfolioItem> portfolioItemList = user.getPortfolioItems();
        PortfolioItem portfolioItem = portfolioItemRepository.findById(itemId).orElseThrow();

        portfolioItemList.remove(portfolioItem);
        portfolioItemRepository.delete(portfolioItem);
        userRepository.save(user);
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
     * Get a page with 3 portfolio items
     *
     * @param userId   Id of the user
     * @param pageable Pageable param
     * @return A page with 3 portfolio items
     */
    public Page<PortfolioItem> findPortfolioItems(String userId, Pageable pageable) {
        return portfolioItemRepository.findByUserId(userId, PageRequest.of(pageable.getPageNumber(), 3));
    }

    /**
     * Save a portfolio item
     * @param portfolioItem A given portfolio item
     */
    public void save(PortfolioItem portfolioItem) {
        portfolioItemRepository.save(portfolioItem);
    }
}
