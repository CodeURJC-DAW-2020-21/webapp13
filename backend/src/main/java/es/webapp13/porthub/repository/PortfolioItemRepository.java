package es.webapp13.porthub.repository;

import es.webapp13.porthub.model.PortfolioItem;
import es.webapp13.porthub.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Long> {

    Page<PortfolioItem> findByUserId(String userId, Pageable pageable);

}
