package gdg.farm_in_palm.domain.monitor.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class MonitorInfoResDTO {
    private Long monitorId;
    private String monitorName;
    private Float temperature;
    private Float humidity;
    private Float groundTemperature;
    private Float groundHumidity;
    private Integer co2Concentration;

    @Builder
    public MonitorInfoResDTO(Long monitorId, String monitorName, Float temperature, Float humidity, Float groundTemperature, Float groundHumidity, Integer co2Concentration) {
        this.monitorId = monitorId;
        this.monitorName = monitorName;
        this.temperature = temperature;
        this.humidity = humidity;
        this.groundTemperature = groundTemperature;
        this.groundHumidity = groundHumidity;
        this.co2Concentration = co2Concentration;
    }


}
