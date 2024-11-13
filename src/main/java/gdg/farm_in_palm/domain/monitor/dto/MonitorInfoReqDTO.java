package gdg.farm_in_palm.domain.monitor.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MonitorInfoReqDTO {
    private String monitorName;
    private Float temperature;
    private Float humidity;
    private Float groundTemperature;
    private Float groundHumidity;
    private Integer co2Concentration;
}
