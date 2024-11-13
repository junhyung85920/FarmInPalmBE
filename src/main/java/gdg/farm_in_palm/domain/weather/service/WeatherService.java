package gdg.farm_in_palm.domain.weather.service;

import gdg.farm_in_palm.domain.weather.Weather;
import gdg.farm_in_palm.domain.weather.dto.WeatherInfoResDTO;
import gdg.farm_in_palm.domain.weather.repository.WeatherRepository;
import gdg.farm_in_palm.exception.CustomException;
import gdg.farm_in_palm.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WeatherService {

    private final WeatherRepository weatherRepository;

    // Weather 조회
    public WeatherInfoResDTO getWeatherById(Long weatherId) {
        Weather weather = weatherRepository.findById(weatherId)
                .orElseThrow(() -> new CustomException(ErrorCode.WEATHER_NOT_FOUND));

        return WeatherInfoResDTO.builder()
                .weatherId(weather.getId())
                .weatherDate(weather.getWeatherDate())
                .temperature(weather.getTemperature())
                .humidity(weather.getHumidity())
                .groundTemperature(weather.getGroundTemperature())
                .groundHumidity(weather.getGroundHumidity())
                .rainGauge(weather.getRainGauge())
                .rainAmount(weather.getRainAmount())
                .build();
    }




}
