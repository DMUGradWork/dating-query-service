package com.grewmeet.dating.datingqueryservice.dto.response;

import java.time.LocalDateTime;

public record EventParticipantResponse(
    Long id,
    Long eventId,
    Long userId,
    String status, // ACTIVE, WITHDRAWN
    LocalDateTime joinedAt
) {}