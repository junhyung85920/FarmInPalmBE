package gdg.farm_in_palm.domain.weather;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="weather_id")
    private Long id;

    private String weatherDate;
    private Float temperature;
    private Float humidity;
    private Float groundTemperature;
    private Float groundHumidity;
    private Float co2Concentration;
    private Integer rainGauge;
    private Integer rainAmount;

    @Builder
    public Weather(Long id, String weatherDate, Float temperature, Float humidity, Float groundTemperature, Float groundHumidity, Float co2Concentration, Integer rainGauge, Integer rainAmount) {
        this.id = id;
        this.weatherDate = weatherDate;
        this.temperature = temperature;
        this.humidity = humidity;
        this.groundTemperature = groundTemperature;
        this.groundHumidity = groundHumidity;
        this.co2Concentration = co2Concentration;
        this.rainGauge = rainGauge;
        this.rainAmount = rainAmount;
    }
}


