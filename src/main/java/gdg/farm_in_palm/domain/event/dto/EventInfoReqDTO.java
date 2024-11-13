package gdg.farm_in_palm.domain.event.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EventInfoReqDTO {
    private String eventTitle;
    private String eventStartDate;
    private String eventStartDay;
    private String eventEndDate;
    private String eventEndDay;
}
