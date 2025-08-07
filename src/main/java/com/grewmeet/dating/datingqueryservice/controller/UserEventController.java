package com.grewmeet.dating.datingqueryservice.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/users/{userId}/events")
public class UserEventController {
    
    @GetMapping
    public ResponseEntity<?> getUserEvents(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        // TODO: 사용자 참여 이벤트 목록 조회 구현
        return ResponseEntity.ok().build();
    }
}