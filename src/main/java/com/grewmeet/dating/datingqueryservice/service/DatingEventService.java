package com.grewmeet.dating.datingqueryservice.service;

import com.grewmeet.dating.datingqueryservice.dto.response.DatingEventResponse;
import com.grewmeet.dating.datingqueryservice.dto.response.EventParticipantResponse;
import com.grewmeet.dating.datingqueryservice.dto.response.PagedResponse;

public interface DatingEventService {
    
    // 이벤트 목록 조회 (페이징)
    PagedResponse<DatingEventResponse> getEvents(int page, int size);
    
    // 이벤트 상세 조회
    DatingEventResponse getEvent(Long eventId);
    
    // 이벤트 참여자 목록 조회
    PagedResponse<EventParticipantResponse> getEventParticipants(Long eventId);
    
    // 사용자 참여 이벤트 목록 조회
    PagedResponse<DatingEventResponse> getUserEvents(Long userId, int page, int size);
    
    // 이벤트 검색
    PagedResponse<DatingEventResponse> searchEvents(String keyword, String location, int page, int size);
}