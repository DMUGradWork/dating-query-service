package com.grewmeet.dating.datingqueryservice.event.handler;

import com.grewmeet.dating.datingqueryservice.saga.DatingMeetingUpdated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DatingMeetingUpdatedHandler {
    
    public void handle(DatingMeetingUpdated event) {
        log.info("Processing DatingMeetingUpdated event: {}", event);
        // TODO: Read Model 업데이트 로직 구현
    }
}