package com.grewmeet.dating.datingqueryservice.event.handler;

import com.grewmeet.dating.datingqueryservice.saga.DatingMeetingDeleted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DatingMeetingDeletedHandler {
    
    public void handle(DatingMeetingDeleted event) {
        log.info("Processing DatingMeetingDeleted event: {}", event);
        // TODO: Read Model 업데이트 로직 구현
    }
}