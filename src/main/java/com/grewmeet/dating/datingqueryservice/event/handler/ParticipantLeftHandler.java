package com.grewmeet.dating.datingqueryservice.event.handler;

import com.grewmeet.dating.datingqueryservice.saga.ParticipantLeft;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ParticipantLeftHandler {
    
    public void handle(ParticipantLeft event) {
        log.info("Processing ParticipantLeft event: {}", event);
        // TODO: Read Model 업데이트 로직 구현
    }
}