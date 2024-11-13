package gdg.farm_in_palm.domain.monitor.repository;

import gdg.farm_in_palm.domain.monitor.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long> {}
