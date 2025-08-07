package com.grewmeet.dating.datingqueryservice.event.handler;

import com.grewmeet.dating.datingqueryservice.saga.DatingMeetingCreated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DatingMeetingCreatedHandler {
    
    public void handle(DatingMeetingCreated event) {
        log.info("Processing DatingMeetingCreated event: {}", event);
        // TODO: Read Model 업데이트 로직 구현
    }
}