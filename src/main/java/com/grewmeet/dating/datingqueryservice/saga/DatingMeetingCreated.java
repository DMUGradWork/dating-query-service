package com.grewmeet.dating.datingqueryservice.saga;

import java.time.LocalDateTime;

public record DatingMeetingCreated(
    Long datingMeetingId,
    String title,
    String description,
    LocalDateTime meetingDateTime,
    String location,
    Integer maxParticipants,
    LocalDateTime createdAt
) {}