package com.grewmeet.dating.datingqueryservice.saga;

import java.time.LocalDateTime;
import java.util.List;

public record DatingMeetingDeleted(
    Long datingMeetingId,
    List<Long> participantIds,
    LocalDateTime deletedAt
) {}