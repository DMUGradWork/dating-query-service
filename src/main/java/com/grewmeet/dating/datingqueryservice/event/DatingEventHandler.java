package com.grewmeet.dating.datingqueryservice.event;

import com.grewmeet.dating.datingqueryservice.saga.*;

public interface DatingEventHandler {
    
    void handleMeetingCreated(DatingMeetingCreated event);
    
    void handleMeetingUpdated(DatingMeetingUpdated event);
    
    void handleMeetingDeleted(DatingMeetingDeleted event);
    
    void handleParticipantJoined(ParticipantJoined event);
    
    void handleParticipantLeft(ParticipantLeft event);
}