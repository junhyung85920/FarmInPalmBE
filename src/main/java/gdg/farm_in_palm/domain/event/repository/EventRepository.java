package gdg.farm_in_palm.domain.event.repository;

import gdg.farm_in_palm.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {}
