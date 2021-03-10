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


    //Add portfolio item to a user by a given id
    public void addPortfolioItem(String id, PortfolioItem item) {
        item.setUserId(id);
        portfolioItemRepository.save(item);

        User user = userRepository.findById(id).orElseThrow();
        user.addPortfolioItem(item);
        userRepository.save(user);
    }

    //Get a portfolio items list
    public Iterable<PortfolioItem> getPortfolioItems(String id) {
        User user = userRepository.findById(id).orElseThrow();
        List<PortfolioItem> portfolioItemList = user.getPortfolioItems();
        return portfolioItemList;
    }

    //Delete a portfolio item by a given userId and itemId
    public void deletePortfolioItem(String userId, long itemId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<PortfolioItem> portfolioItemList = user.getPortfolioItems();
        PortfolioItem portfolioItem = portfolioItemRepository.findById(itemId).orElseThrow();

        portfolioItemList.remove(portfolioItem);
        portfolioItemRepository.delete(portfolioItem);
        userRepository.save(user);
    }

}
