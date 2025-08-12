package com.grewmeet.dating.datingqueryservice.event;

import com.grewmeet.dating.datingqueryservice.saga.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaEventListener {
    
    private final DatingEventHandler eventHandler;
    
    @KafkaListener(topics = "dating-meeting-created", groupId = "dating-query-service")
    public void handleDatingMeetingCreated(
            @Payload DatingMeetingCreated event,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset,
            Acknowledgment ack) {
        
        log.info("Received DatingMeetingCreated event from topic: {}, partition: {}, offset: {}, event: {}", 
                topic, partition, offset, event);
        
        try {
            eventHandler.handleMeetingCreated(event);
            ack.acknowledge();
            log.info("Successfully processed DatingMeetingCreated event: {}", event.datingMeetingId());
        } catch (Exception e) {
            log.error("Failed to process DatingMeetingCreated event: {}", event, e);
            // 추후 DLQ 처리나 재시도 로직 추가 가능
            throw e;
        }
    }
    
    @KafkaListener(topics = "dating-meeting-updated", groupId = "dating-query-service")
    public void handleDatingMeetingUpdated(
            @Payload DatingMeetingUpdated event,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            Acknowledgment ack) {
        
        log.info("Received DatingMeetingUpdated event from topic: {}, event: {}", topic, event);
        
        try {
            eventHandler.handleMeetingUpdated(event);
            ack.acknowledge();
            log.info("Successfully processed DatingMeetingUpdated event: {}", event.datingMeetingId());
        } catch (Exception e) {
            log.error("Failed to process DatingMeetingUpdated event: {}", event, e);
            throw e;
        }
    }
    
    @KafkaListener(topics = "dating-meeting-deleted", groupId = "dating-query-service")
    public void handleDatingMeetingDeleted(
            @Payload DatingMeetingDeleted event,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            Acknowledgment ack) {
        
        log.info("Received DatingMeetingDeleted event from topic: {}, event: {}", topic, event);
        
        try {
            eventHandler.handleMeetingDeleted(event);
            ack.acknowledge();
            log.info("Successfully processed DatingMeetingDeleted event: {}", event.datingMeetingId());
        } catch (Exception e) {
            log.error("Failed to process DatingMeetingDeleted event: {}", event, e);
            throw e;
        }
    }
    
    @KafkaListener(topics = "participant-joined", groupId = "dating-query-service")
    public void handleParticipantJoined(
            @Payload ParticipantJoined event,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            Acknowledgment ack) {
        
        log.info("Received ParticipantJoined event from topic: {}, event: {}", topic, event);
        
        try {
            eventHandler.handleParticipantJoined(event);
            ack.acknowledge();
            log.info("Successfully processed ParticipantJoined event: meetingId={}, participantId={}", 
                    event.datingMeetingId(), event.participantId());
        } catch (Exception e) {
            log.error("Failed to process ParticipantJoined event: {}", event, e);
            throw e;
        }
    }
    
    @KafkaListener(topics = "participant-left", groupId = "dating-query-service")
    public void handleParticipantLeft(
            @Payload ParticipantLeft event,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            Acknowledgment ack) {
        
        log.info("Received ParticipantLeft event from topic: {}, event: {}", topic, event);
        
        try {
            eventHandler.handleParticipantLeft(event);
            ack.acknowledge();
            log.info("Successfully processed ParticipantLeft event: meetingId={}, participantId={}", 
                    event.datingMeetingId(), event.participantId());
        } catch (Exception e) {
            log.error("Failed to process ParticipantLeft event: {}", event, e);
            throw e;
        }
    }
}