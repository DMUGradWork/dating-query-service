package com.grewmeet.dating.datingqueryservice.service;

import com.grewmeet.dating.datingqueryservice.domain.DatingEvent;
import com.grewmeet.dating.datingqueryservice.dto.response.DatingEventResponse;
import com.grewmeet.dating.datingqueryservice.dto.response.EventParticipantResponse;
import com.grewmeet.dating.datingqueryservice.dto.response.PagedResponse;
import com.grewmeet.dating.datingqueryservice.repository.DatingEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DatingEventServiceImpl implements DatingEventService {
    
    private final DatingEventRepository datingEventRepository;
    
    @Override
    public PagedResponse<DatingEventResponse> getEvents(int page, int size) {
        log.info("Fetching events - page: {}, size: {}", page, size);
        
        Pageable pageable = PageRequest.of(page, size);
        Page<DatingEvent> eventsPage = datingEventRepository
                .findAllByOrderByCreatedAtDesc(pageable);
        
        List<DatingEventResponse> content = eventsPage.getContent()
                .stream()
                .map(this::convertToResponse)
                .toList();
        
        return PagedResponse.from(eventsPage, content);
    }
    
    @Override
    public DatingEventResponse getEvent(Long eventId) {
        log.info("Fetching event by id: {}", eventId);
        
        DatingEvent event = datingEventRepository.findByEventId(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found: " + eventId));
        
        return convertToResponse(event);
    }
    
    @Override
    public PagedResponse<EventParticipantResponse> getEventParticipants(Long eventId) {
        // TODO: EventParticipant 조회 로직 구현
        log.info("Fetching participants for event: {}", eventId);
        throw new RuntimeException("Not implemented yet");
    }
    
    @Override
    public PagedResponse<DatingEventResponse> getUserEvents(Long userId, int page, int size) {
        // TODO: 사용자별 이벤트 조회 로직 구현
        log.info("Fetching events for user: {}, page: {}, size: {}", userId, page, size);
        throw new RuntimeException("Not implemented yet");
    }
    
    @Override
    public PagedResponse<DatingEventResponse> searchEvents(String keyword, String location, int page, int size) {
        log.info("Searching events - keyword: {}, location: {}, page: {}, size: {}", 
                keyword, location, page, size);
        
        Pageable pageable = PageRequest.of(page, size);
        Page<DatingEvent> eventsPage;
        
        if (keyword != null && !keyword.trim().isEmpty()) {
            eventsPage = datingEventRepository.findByKeyword(keyword.trim(), pageable);
        } else {
            // 키워드가 없으면 전체 조회
            eventsPage = datingEventRepository.findAllByOrderByCreatedAtDesc(pageable);
        }
        
        List<DatingEventResponse> content = eventsPage.getContent()
                .stream()
                .map(this::convertToResponse)
                .toList();
        
        return PagedResponse.from(eventsPage, content);
    }
    
    /**
     * Domain 객체를 Response DTO로 변환
     */
    private DatingEventResponse convertToResponse(DatingEvent event) {
        return new DatingEventResponse(
                event.getEventId(), // Long eventId 사용 (비즈니스 키)
                event.getTitle(),
                event.getDescription(),
                event.getMeetingDateTime(),
                event.getLocation(),
                event.getMaxParticipants(),
                event.getCurrentParticipants(),
                event.getStatus() != null ? event.getStatus().name() : null,
                event.getCreatedAt(),
                event.getUpdatedAt()
        );
    }
}