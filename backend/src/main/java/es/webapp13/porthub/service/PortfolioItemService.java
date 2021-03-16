package es.webapp13.porthub.service;

import es.webapp13.porthub.repository.PortfolioItemRepository;
import es.webapp13.porthub.repository.UserRepository;
import es.webapp13.porthub.model.PortfolioItem;
import es.webapp13.porthub.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class PortfolioItemService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PortfolioItemRepository portfolioItemRepository;


    /**
     * Add portfolio item to a user by a given id
     *
     * @param userId Id of the user
     * @param item   Form with the portfolio item information
     */
    public void addPortfolioItem(String userId, PortfolioItem item) {
        item.setUserId(userId);
        portfolioItemRepository.save(item);

        User user = userRepository.findById(userId).orElseThrow();
        user.addPortfolioItem(item);
        userRepository.save(user);
    }

    /**
     * Get a portfolio item by a given user id
     *
     * @param userId Id of the user
     * @return Iterable of portfolio items
     */
    public List<PortfolioItem> getPortfolioItems(String userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return user.getPortfolioItems();
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
    public void updatePortfolioItem(PortfolioItem item, long id) throws IOException {
        PortfolioItem portfolioItem = portfolioItemRepository.findById(id).orElseThrow();
        portfolioItem.updatePreviewImg(item.getPreviewImg());
        portfolioItem.updateImage1(item.getImage1());
        portfolioItem.updateImage2(item.getImage2());
        portfolioItem.updateImage3(item.getImage3());
        portfolioItem.setName(item.getName());
        portfolioItem.setCategory(item.getCategory());
        portfolioItem.setClient(item.getClient());
        portfolioItem.setDate(item.getDate());
        portfolioItem.setUrl(item.getUrl());
        portfolioItem.setDescription(item.getDescription());
        portfolioItemRepository.save(portfolioItem);
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

    public Optional<PortfolioItem> findById(long id){
        return portfolioItemRepository.findById(id);
    }

}
