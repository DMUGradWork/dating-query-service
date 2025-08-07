package com.grewmeet.dating.datingqueryservice.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "event_participants")
@CompoundIndex(name = "event_user_idx", def = "{'eventId': 1, 'userId': 1}", unique = true)
public class EventParticipant {
    @Id
    private String id; // MongoDB String id
    
    private Long participantId; // 원본 참여자 ID (비즈니스 키)
    
    @Indexed
    private Long eventId;
    
    @Indexed
    private Long userId;
    
    @Indexed
    private ParticipantStatus status;
    
    @Indexed
    private LocalDateTime joinedAt;
}