package com.grewmeet.dating.datingqueryservice.saga;

import java.time.LocalDateTime;

public record ParticipantJoined(
    Long datingMeetingId,
    Long participantId,
    Long userId,
    LocalDateTime joinedAt
) {}