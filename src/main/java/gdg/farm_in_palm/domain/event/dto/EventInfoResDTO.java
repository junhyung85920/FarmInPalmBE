package gdg.farm_in_palm.domain.event.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EventInfoResDTO {
    private Long eventId;
    private String eventTitle;
    private String eventStartDate;
    private String eventStartDay;
    private String eventEndDate;
    private String eventEndDay;

    @Builder
    public EventInfoResDTO(Long eventId, String eventTitle, String eventStartDate, String eventStartDay, String eventEndDate, String eventEndDay) {
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.eventStartDate = eventStartDate;
        this.eventStartDay = eventStartDay;
        this.eventEndDate = eventEndDate;
        this.eventEndDay = eventEndDay;
    }

}
