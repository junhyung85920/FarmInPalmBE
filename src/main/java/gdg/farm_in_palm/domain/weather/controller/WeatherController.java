package gdg.farm_in_palm.domain.weather.controller;

import gdg.farm_in_palm.domain.weather.dto.WeatherInfoResDTO;
import gdg.farm_in_palm.domain.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    // Weather 조회
    @GetMapping
    public WeatherInfoResDTO getWeather() throws IOException {
        return weatherService.getWeather();
    }
}
