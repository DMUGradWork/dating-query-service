package com.grewmeet.dating.datingqueryservice.dto.response;

import java.time.LocalDateTime;

public record DatingEventResponse(
    Long id,
    String title,
    String description,
    LocalDateTime meetingDateTime,
    String location,
    Integer maxParticipants,
    Integer currentParticipants,
    String status, // ACTIVE, FULL, CANCELLED
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}