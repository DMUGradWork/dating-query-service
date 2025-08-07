package com.grewmeet.dating.datingqueryservice.event;

import com.grewmeet.dating.datingqueryservice.saga.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DatingEventHandlerImpl implements DatingEventHandler {
    
    @Override
    public void handleMeetingCreated(DatingMeetingCreated event) {
        log.info("Processing DatingMeetingCreated event: {}", event);
        // TODO: Read Model 업데이트 로직 구현
        // 1. DatingEvent 엔티티 생성
        // 2. 데이터베이스에 저장
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