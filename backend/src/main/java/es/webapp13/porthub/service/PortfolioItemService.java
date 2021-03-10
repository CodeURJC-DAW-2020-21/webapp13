package es.webapp13.porthub.service;

import es.webapp13.porthub.Repository.PortfolioItemRepository;
import es.webapp13.porthub.Repository.UserRepository;
import es.webapp13.porthub.model.PortfolioItem;
import es.webapp13.porthub.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PortfolioItemService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PortfolioItemRepository portfolioItemRepository;


    /**
     * Add portfolio item to a user by a given id
     *
     * @param userId id of the user
     * @param item   form with the portfolio item information
     */
    public void addPortfolioItem(String userId, PortfolioItem item) {
        item.setUserId(userId);
        portfolioItemRepository.save(item);

        User user = userRepository.findById(userId).orElseThrow();
        user.addPortfolioItem(item);
        userRepository.save(user);
    }

    /**
     * Get a portfolio item by a given id
     *
     * @param userId id of the user
     * @return Iterable of portfolio items
     */
    public List<PortfolioItem> getPortfolioItems(String userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return user.getPortfolioItems();
    }


    /**
     * Update a portfolio item fields
     *
     * @param item form with the changes to the portfolio item
     * @param id   id of the portfolio item
     */
    public void updatePortfolioItem(PortfolioItem item, long id) {
        PortfolioItem portfolioItem = portfolioItemRepository.findById(id).orElseThrow();
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

}
