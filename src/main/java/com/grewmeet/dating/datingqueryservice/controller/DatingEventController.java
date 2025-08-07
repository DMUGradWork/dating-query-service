package com.grewmeet.dating.datingqueryservice.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/events")
public class DatingEventController {
    
    @GetMapping
    public ResponseEntity<?> getEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        // TODO: 이벤트 목록 조회 구현
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{eventId}")
    public ResponseEntity<?> getEvent(@PathVariable Long eventId) {
        // TODO: 이벤트 상세 조회 구현
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{eventId}/participants")
    public ResponseEntity<?> getEventParticipants(@PathVariable Long eventId) {
        // TODO: 이벤트 참여자 목록 조회 구현
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/search")
    public ResponseEntity<?> searchEvents(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String location) {
        // TODO: 이벤트 검색 구현
        return ResponseEntity.ok().build();
    }
}