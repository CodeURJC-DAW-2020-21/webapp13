package es.webapp13.porthub.repository;

import es.webapp13.porthub.model.PortfolioItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Long> {


}
