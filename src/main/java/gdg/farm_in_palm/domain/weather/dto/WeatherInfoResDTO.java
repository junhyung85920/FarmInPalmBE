package gdg.farm_in_palm.domain.weather.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WeatherInfoResDTO {
    private Long weatherId;
    private String weatherDate;
    private Float temperature;
    private Float humidity;
    private Float groundTemperature;
    private Float groundHumidity;
    private Integer rainGauge;
    private Integer rainAmount;

    @Builder
    public WeatherInfoResDTO(Long weatherId, String weatherDate, Float temperature, Float humidity, Float groundTemperature, Float groundHumidity, Integer rainGauge, Integer rainAmount) {
        this.weatherId = weatherId;
        this.weatherDate = weatherDate;
        this.temperature = temperature;
        this.humidity = humidity;
        this.groundTemperature = groundTemperature;
        this.groundHumidity = groundHumidity;
        this.rainGauge = rainGauge;
        this.rainAmount = rainAmount;
    }
}
