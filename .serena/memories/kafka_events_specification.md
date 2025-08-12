# Kafka Events Specification

## 구독해야 할 이벤트 목록

### DatingMeetingCreated
```json
{
  "datingMeetingId": Long,
  "title": String,
  "description": String,
  "meetingDateTime": LocalDateTime,
  "location": String,
  "maxParticipants": Integer,
  "createdAt": LocalDateTime
}
```

### DatingMeetingUpdated
```json
{
  "datingMeetingId": Long,
  "title": String,
  "description": String,
  "meetingDateTime": LocalDateTime,
  "location": String,
  "maxParticipants": Integer,
  "updatedAt": LocalDateTime
}
```

### DatingMeetingDeleted
```json
{
  "datingMeetingId": Long,
  "participantIds": List<Long>,
  "deletedAt": LocalDateTime
}
```

### ParticipantJoined
```json
{
  "datingMeetingId": Long,
  "participantId": Long,
  "userId": Long,
  "joinedAt": LocalDateTime
}
```

### ParticipantLeft
```json
{
  "datingMeetingId": Long,
  "participantId": Long,
  "userId": Long,
  "leftAt": LocalDateTime
}
```

## Kafka 설정 고려사항
- **Consumer Group**: 각 이벤트 타입별 독립적인 컨슈머 그룹
- **Offset Management**: 수동 커밋으로 정확한 이벤트 처리 보장
- **Error Handling**: 실패한 이벤트에 대한 재시도 및 DLQ 처리
- **Serialization**: JSON 기반 직렬화/역직렬화
- **멱등성**: 동일 이벤트 중복 처리 방지 (이벤트 ID 기반)