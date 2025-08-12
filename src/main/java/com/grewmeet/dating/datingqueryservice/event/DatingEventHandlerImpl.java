package com.grewmeet.dating.datingqueryservice.event;

import com.grewmeet.dating.datingqueryservice.domain.DatingEvent;
import com.grewmeet.dating.datingqueryservice.domain.EventStatus;
import com.grewmeet.dating.datingqueryservice.repository.DatingEventRepository;
import com.grewmeet.dating.datingqueryservice.saga.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 데이팅 이벤트 처리 구현체
 * EventHandler 인터페이스를 구현하여 Kafka에서 수신한 이벤트를 처리하고
 * Read Model을 업데이트합니다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DatingEventHandlerImpl implements DatingEventHandler {
    
    private final DatingEventRepository datingEventRepository;
    
    @Override
    public void handleMeetingCreated(DatingMeetingCreated event) {
        log.info("Processing DatingMeetingCreated event: {}", event);
        
        try {
            // 중복 이벤트 처리 방지 - 이미 존재하는지 확인
            if (datingEventRepository.findByEventId(event.datingMeetingId()).isPresent()) {
                log.warn("Dating event already exists with ID: {}", event.datingMeetingId());
                return;
            }
            
            // DatingEvent 엔티티 생성
            DatingEvent datingEvent = DatingEvent.builder()
                    .eventId(event.datingMeetingId())
                    .title(event.title())
                    .description(event.description())
                    .meetingDateTime(event.meetingDateTime())
                    .location(event.location())
                    .maxParticipants(event.maxParticipants())
                    .currentParticipants(0) // 초기값 0
                    .status(EventStatus.ACTIVE) // 초기 상태는 ACTIVE
                    .createdAt(event.createdAt())
                    .updatedAt(event.createdAt())
                    .build();
            
            // 데이터베이스에 저장
            DatingEvent savedEvent = datingEventRepository.save(datingEvent);
            log.info("Successfully created dating event: {}", savedEvent.getEventId());
            
        } catch (Exception e) {
            log.error("Failed to process DatingMeetingCreated event: {}", event, e);
            throw new RuntimeException("Failed to create dating event", e);
        }
    }
    
    @Override
    public void handleMeetingUpdated(DatingMeetingUpdated event) {
        log.info("Processing DatingMeetingUpdated event: {}", event);
        // TODO: Read Model 업데이트 로직 구현
        // 1. 기존 DatingEvent 조회
        // 2. 필드 업데이트
        // 3. 데이터베이스 저장
    }
    
    @Override
    public void handleMeetingDeleted(DatingMeetingDeleted event) {
        log.info("Processing DatingMeetingDeleted event: {}", event);
        // TODO: Read Model 업데이트 로직 구현
        // 1. DatingEvent 상태를 CANCELLED로 변경
        // 2. 모든 참여자 상태를 WITHDRAWN으로 변경
    }
    
    @Override
    public void handleParticipantJoined(ParticipantJoined event) {
        log.info("Processing ParticipantJoined event: {}", event);
        // TODO: Read Model 업데이트 로직 구현
        // 1. EventParticipant 엔티티 생성
        // 2. DatingEvent의 currentParticipants 증가
        // 3. 정원이 찬 경우 status를 FULL로 변경
    }
    
    @Override
    public void handleParticipantLeft(ParticipantLeft event) {
        log.info("Processing ParticipantLeft event: {}", event);
        // TODO: Read Model 업데이트 로직 구현
        // 1. EventParticipant 상태를 WITHDRAWN으로 변경
        // 2. DatingEvent의 currentParticipants 감소
        // 3. FULL 상태였다면 ACTIVE로 변경
    }
}