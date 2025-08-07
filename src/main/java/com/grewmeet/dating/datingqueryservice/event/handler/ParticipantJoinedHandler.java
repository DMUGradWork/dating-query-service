package com.grewmeet.dating.datingqueryservice.event.handler;

import com.grewmeet.dating.datingqueryservice.saga.ParticipantJoined;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ParticipantJoinedHandler {
    
    public void handle(ParticipantJoined event) {
        log.info("Processing ParticipantJoined event: {}", event);
        // TODO: Read Model 업데이트 로직 구현
    }
}