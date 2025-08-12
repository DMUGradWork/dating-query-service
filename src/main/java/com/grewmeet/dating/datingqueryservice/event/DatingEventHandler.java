package com.grewmeet.dating.datingqueryservice.event;

import com.grewmeet.dating.datingqueryservice.saga.*;

/**
 * 데이팅 이벤트 처리 인터페이스
 * 
 * Kafka에서 수신한 도메인 이벤트를 처리하여 Query-side Read Model을 업데이트하는
 * 계약을 정의합니다.
 */
public interface DatingEventHandler {
    
    /**
     * 데이팅 미팅 생성 이벤트를 처리합니다.
     * 
     * @param event 데이팅 미팅 생성 이벤트
     */
    void handleMeetingCreated(DatingMeetingCreated event);
    
    /**
     * 데이팅 미팅 수정 이벤트를 처리합니다.
     * 
     * @param event 데이팅 미팅 수정 이벤트
     */
    void handleMeetingUpdated(DatingMeetingUpdated event);
    
    /**
     * 데이팅 미팅 삭제 이벤트를 처리합니다.
     * 
     * @param event 데이팅 미팅 삭제 이벤트
     */
    void handleMeetingDeleted(DatingMeetingDeleted event);
    
    /**
     * 참여자 참가 이벤트를 처리합니다.
     * 
     * @param event 참여자 참가 이벤트
     */
    void handleParticipantJoined(ParticipantJoined event);
    
    /**
     * 참여자 탈퇴 이벤트를 처리합니다.
     * 
     * @param event 참여자 탈퇴 이벤트
     */
    void handleParticipantLeft(ParticipantLeft event);
}