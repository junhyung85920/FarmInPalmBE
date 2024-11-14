package gdg.farm_in_palm.domain.event.controller;

import gdg.farm_in_palm.domain.common.dto.SuccessResDTO;
import gdg.farm_in_palm.domain.event.dto.EventInfoReqDTO;
import gdg.farm_in_palm.domain.event.dto.EventInfoResDTO;
import gdg.farm_in_palm.domain.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    // 모든 Event 조회
    @GetMapping
    public List<EventInfoResDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    // 특정 Event 조회
    @GetMapping("/{eventId}")
    public EventInfoResDTO getEventById(Long eventId) {
        return eventService.getEventById(eventId);
    }

    // Event 생성
    @PostMapping
    public EventInfoResDTO createEvent(EventInfoReqDTO eventInfoReqDTO) {
        return eventService.createEvent(eventInfoReqDTO);
    }

    // Event 수정
    @PutMapping
    public EventInfoResDTO updateEvent(Long eventId, EventInfoReqDTO eventInfoReqDTO) {
        return eventService.updateEvent(eventId, eventInfoReqDTO);
    }

    // Event 삭제
    @DeleteMapping("/{eventId}")
    public SuccessResDTO deleteEvent(Long eventId) {
        return eventService.deleteEvent(eventId);
    }
}
