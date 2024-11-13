package gdg.farm_in_palm.domain.weather.repository;

import gdg.farm_in_palm.domain.weather.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
}
