package gdg.farm_in_palm.domain.weather.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class WeatherInfoReqDTO {
    private String weatherName;
    private String weatherDate;
    private Float temperature;
    private Float humidity;
    private Float groundTemperature;
    private Float groundHumidity;
    private Integer rainGauge;
    private Integer rainAmount;
}
