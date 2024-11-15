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
    @GetMapping("/{userId}")
    public List<EventInfoResDTO> getAllEventsByUserId(@PathVariable("userId") Long userId) {
        return eventService.getAllEventsByUserId(userId);
    }

    // 특정 Event 조회
    @GetMapping("/detail/{eventId}")
    public EventInfoResDTO getEventById(@PathVariable("eventId") Long eventId) {
        return eventService.getEventById(eventId);
    }

    // Event 생성
    @PostMapping("/detail")
    public EventInfoResDTO createEvent(@RequestBody EventInfoReqDTO eventInfoReqDTO) {
        return eventService.createEvent(eventInfoReqDTO);
    }

    // Event 수정
    @PutMapping("/detail/{eventId}")
    public EventInfoResDTO updateEvent(@PathVariable("eventId") Long eventId, @RequestBody EventInfoReqDTO eventInfoReqDTO) {
        return eventService.updateEvent(eventId, eventInfoReqDTO);
    }

    // Event 삭제
    @DeleteMapping("/detail/{eventId}")
    public SuccessResDTO deleteEvent(@PathVariable("eventId") Long eventId) {
        return eventService.deleteEvent(eventId);
    }
}
