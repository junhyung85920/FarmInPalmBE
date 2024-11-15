package gdg.farm_in_palm.domain.event.service;

import gdg.farm_in_palm.domain.common.dto.SuccessResDTO;
import gdg.farm_in_palm.domain.event.Event;
import gdg.farm_in_palm.domain.event.dto.EventInfoReqDTO;
import gdg.farm_in_palm.domain.event.dto.EventInfoResDTO;
import gdg.farm_in_palm.domain.event.repository.EventRepository;
import gdg.farm_in_palm.exception.CustomException;
import gdg.farm_in_palm.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventService {

    private final EventRepository eventRepository;

    // 모든 Event 조회
    public List<EventInfoResDTO> getAllEventsByUserId(Long userId) {
        List<Event> events = eventRepository.findByUserId(userId);
        List<EventInfoResDTO> eventInfoResDTOs = new ArrayList<>();

        for (Event event : events) {
            EventInfoResDTO eventInfoResDTO = EventInfoResDTO.builder()
                    .eventId(event.getId())
                    .eventTitle(event.getEventTitle())
                    .eventStartDate(event.getEventStartDate())
                    .eventStartDay(event.getEventStartDay())
                    .eventEndDate(event.getEventEndDate())
                    .eventEndDay(event.getEventEndDay())
                    .build();

            eventInfoResDTOs.add(eventInfoResDTO);
        }

        return eventInfoResDTOs;
    }

    // 특정 Event 조회
    public EventInfoResDTO getEventById(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new CustomException(ErrorCode.EVENT_NOT_FOUND));

        return EventInfoResDTO.builder()
                .eventId(event.getId())
                .eventTitle(event.getEventTitle())
                .eventStartDate(event.getEventStartDate())
                .eventStartDay(event.getEventStartDay())
                .eventEndDate(event.getEventEndDate())
                .eventEndDay(event.getEventEndDay())
                .build();
    }

    // Event 생성
    @Transactional
    public EventInfoResDTO createEvent(EventInfoReqDTO eventInfoReqDTO) {
        Event event = Event.builder()
                .eventTitle(eventInfoReqDTO.getEventTitle())
                .eventStartDate(eventInfoReqDTO.getEventStartDate())
                .eventStartDay(eventInfoReqDTO.getEventStartDay())
                .eventEndDate(eventInfoReqDTO.getEventEndDate())
                .eventEndDay(eventInfoReqDTO.getEventEndDay())
                .build();

        eventRepository.save(event);

        return EventInfoResDTO.builder()
                .eventId(event.getId())
                .eventTitle(event.getEventTitle())
                .eventStartDate(event.getEventStartDate())
                .eventStartDay(event.getEventStartDay())
                .eventEndDate(event.getEventEndDate())
                .eventEndDay(event.getEventEndDay())
                .build();
    }

    // Event 수정
    @Transactional
    public EventInfoResDTO updateEvent(Long eventId, EventInfoReqDTO eventInfoReqDTO) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new CustomException(ErrorCode.EVENT_NOT_FOUND));

        event.setEventTitle(eventInfoReqDTO.getEventTitle());
        event.setEventStartDate(eventInfoReqDTO.getEventStartDate());
        event.setEventStartDay(eventInfoReqDTO.getEventStartDay());
        event.setEventEndDate(eventInfoReqDTO.getEventEndDate());
        event.setEventEndDay(eventInfoReqDTO.getEventEndDay());

        eventRepository.save(event);

        return EventInfoResDTO.builder()
                .eventId(event.getId())
                .eventTitle(event.getEventTitle())
                .eventStartDate(event.getEventStartDate())
                .eventStartDay(event.getEventStartDay())
                .eventEndDate(event.getEventEndDate())
                .eventEndDay(event.getEventEndDay())
                .build();
    }

    // Event 삭제
    @Transactional
    public SuccessResDTO deleteEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new CustomException(ErrorCode.EVENT_NOT_FOUND));
        eventRepository.delete(event);

        return SuccessResDTO.builder()
                .success(true)
                .build();
    }
}
