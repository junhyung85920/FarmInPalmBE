package gdg.farm_in_palm.domain.event;


import gdg.farm_in_palm.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_id")
    private Long id;

    private String eventTitle;
    private String eventStartDate;
    private String eventStartDay;
    private String eventEndDate;
    private String eventEndDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Builder
    public Event(Long id, String eventTitle, String eventStartDate, String eventStartDay, String eventEndDate, String eventEndDay, User user) {
        this.id = id;
        this.eventTitle = eventTitle;
        this.eventStartDate = eventStartDate;
        this.eventStartDay = eventStartDay;
        this.eventEndDate = eventEndDate;
        this.eventEndDay = eventEndDay;
        this.user = user;
    }
}
