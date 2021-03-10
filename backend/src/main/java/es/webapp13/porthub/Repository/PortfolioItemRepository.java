package es.webapp13.porthub.Repository;

import es.webapp13.porthub.model.PortfolioItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Long> {


}
