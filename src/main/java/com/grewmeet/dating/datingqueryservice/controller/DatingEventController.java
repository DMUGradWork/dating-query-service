package com.grewmeet.dating.datingqueryservice.controller;

import com.grewmeet.dating.datingqueryservice.dto.response.DatingEventResponse;
import com.grewmeet.dating.datingqueryservice.dto.response.EventParticipantResponse;
import com.grewmeet.dating.datingqueryservice.dto.response.PagedResponse;
import com.grewmeet.dating.datingqueryservice.service.DatingEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class DatingEventController {
    
    private final DatingEventService datingEventService;
    
    @GetMapping
    public ResponseEntity<PagedResponse<DatingEventResponse>> getEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("GET /events - page: {}, size: {}", page, size);
        
        PagedResponse<DatingEventResponse> response = datingEventService.getEvents(page, size);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{eventId}")
    public ResponseEntity<DatingEventResponse> getEvent(@PathVariable Long eventId) {
        log.info("GET /events/{}", eventId);
        
        DatingEventResponse response = datingEventService.getEvent(eventId);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{eventId}/participants")
    public ResponseEntity<PagedResponse<EventParticipantResponse>> getEventParticipants(
            @PathVariable Long eventId) {
        log.info("GET /events/{}/participants", eventId);
        
        PagedResponse<EventParticipantResponse> response = 
                datingEventService.getEventParticipants(eventId);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/search")
    public ResponseEntity<PagedResponse<DatingEventResponse>> searchEvents(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String location,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("GET /events/search - keyword: {}, location: {}, page: {}, size: {}", 
                keyword, location, page, size);
        
        PagedResponse<DatingEventResponse> response = 
                datingEventService.searchEvents(keyword, location, page, size);
        return ResponseEntity.ok(response);
    }
}