package gdg.farm_in_palm.domain.stock.repository;

import gdg.farm_in_palm.domain.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}
