package com.grewmeet.dating.datingqueryservice.saga;

import java.time.LocalDateTime;

public record ParticipantLeft(
    Long datingMeetingId,
    Long participantId,
    Long userId,
    LocalDateTime leftAt
) {}