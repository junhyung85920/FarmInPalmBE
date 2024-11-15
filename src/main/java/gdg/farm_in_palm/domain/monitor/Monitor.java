package gdg.farm_in_palm.domain.monitor;

import gdg.farm_in_palm.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Monitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="monitor_id")
    private Long id;

    private String monitorName;
    private Float temperature;
    private Float humidity;
    private Float groundTemperature;
    private Float groundHumidity;
    private Float co2Concentration;
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Builder
    public Monitor(Long id, String monitorName, Float temperature, Float humidity, Float groundTemperature, Float groundHumidity, Float co2Concentration, String date, User user) {
        this.id = id;
        this.monitorName = monitorName;
        this.temperature = temperature;
        this.humidity = humidity;
        this.groundTemperature = groundTemperature;
        this.groundHumidity = groundHumidity;
        this.co2Concentration = co2Concentration;
        this.date = date;
        this.user = user;
    }
}
