// MongoDB 초기화 스크립트
db = db.getSiblingDB('dating_query');

// 데이팅 이벤트 컬렉션 생성 및 인덱스 설정
db.createCollection('dating_events');
db.dating_events.createIndex({ "id": 1 }, { unique: true });
db.dating_events.createIndex({ "title": "text", "description": "text", "location": "text" });
db.dating_events.createIndex({ "meetingDateTime": 1 });
db.dating_events.createIndex({ "location": 1 });
db.dating_events.createIndex({ "status": 1 });
db.dating_events.createIndex({ "createdAt": -1 });

// 이벤트 참여자 컬렉션 생성 및 인덱스 설정
db.createCollection('event_participants');
db.event_participants.createIndex({ "id": 1 }, { unique: true });
db.event_participants.createIndex({ "eventId": 1 });
db.event_participants.createIndex({ "userId": 1 });
db.event_participants.createIndex({ "eventId": 1, "userId": 1 }, { unique: true });
db.event_participants.createIndex({ "status": 1 });
db.event_participants.createIndex({ "joinedAt": -1 });

print('MongoDB 초기화 완료: dating_query 데이터베이스와 컬렉션이 생성되었습니다.');