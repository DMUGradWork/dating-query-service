package com.grewmeet.dating.datingqueryservice.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "dating_events")
public class DatingEvent {
    @Id
    private String id; // MongoDB는 String id 사용
    
    private Long eventId; // 원본 이벤트 ID (비즈니스 키)
    
    @TextIndexed
    private String title;
    
    @TextIndexed
    private String description;
    
    @Indexed
    private LocalDateTime meetingDateTime;
    
    @TextIndexed
    private String location;
    
    private Integer maxParticipants;
    
    private Integer currentParticipants;
    
    @Indexed
    private EventStatus status;
    
    @Indexed
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}