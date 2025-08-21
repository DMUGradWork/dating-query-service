# Dating Query Service

데이팅 도메인의 쿼리 서비스 (데이팅 이벤트 조회 전담)

## 🔧 환경 설정
- **포트**: 8081 (기본값)
- **데이터베이스**: MongoDB (Read Model 저장용)
- **Kafka**: localhost:9092 (이벤트 구독용)

## 📡 이벤트 구독
CQRS Query Side로서 Command Service에서 발행하는 모든 이벤트 구독:

| 이벤트 타입 | 처리 상태 | 설명 | Read Model 업데이트 |
|------------|----------|------|-------------------|
| `DatingMeetingCreated` | ✅ 구현완료 | 데이팅 이벤트 생성 | DatingEvent 생성 |
| `DatingMeetingUpdated` | ⚠️ TODO | 데이팅 이벤트 수정 | DatingEvent 업데이트 |
| `DatingMeetingDeleted` | ⚠️ TODO | 데이팅 이벤트 삭제 | DatingEvent 상태 변경 |
| `ParticipantJoined` | ⚠️ TODO | 이벤트 참여 | EventParticipant 생성, 참여자 수 증가 |
| `ParticipantLeft` | ⚠️ TODO | 이벤트 탈퇴 | EventParticipant 상태 변경, 참여자 수 감소 |

### ⚠️ Command Service와의 스키마 불일치 이슈
현재 이벤트 스키마에 다음 문제들이 있어 수정이 필요합니다:

1. **필드명 불일치**:
   - Command: `eventId` → Query: `datingMeetingId`

2. **데이터 타입 불일치**:
   - Command: `userId: String` → Query: `userId: Long`

## 🚀 실행
```bash
./gradlew bootRun
```

## 📋 API 엔드포인트

### 이벤트 조회
- **GET** `/events` - 이벤트 목록 조회 (페이징)
- **GET** `/events/{eventId}` - 이벤트 상세 조회
- **GET** `/events/{eventId}/participants` - 이벤트 참여자 목록
- **GET** `/events/search` - 이벤트 검색 (키워드, 지역)

### 사용자별 조회
- **GET** `/users/{userId}/events` - 사용자 참여 이벤트 목록

## 🏗️ 아키텍처

<details>
<summary><strong>기술 스택</strong></summary>

- **프레임워크**: Spring Boot 3.5.4
- **Java 버전**: 21
- **빌드 도구**: Gradle with Wrapper
- **데이터베이스**: MongoDB (조회 최적화용 NoSQL)
- **메시징**: Apache Kafka (이벤트 구독)
- **아키텍처**: CQRS Query Side + Event-Driven Architecture

</details>

<details>
<summary><strong>Read Model 설계</strong></summary>

**DatingEvent** (MongoDB Collection: `dating_events`)
```java
{
  "_id": ObjectId,           // MongoDB 내부 ID
  "eventId": Long,           // 비즈니스 키 (Command Service의 ID)
  "title": String,           // 텍스트 인덱스
  "description": String,     // 텍스트 인덱스
  "meetingDateTime": DateTime, // 인덱스
  "location": String,        // 텍스트 인덱스
  "maxParticipants": Integer,
  "currentParticipants": Integer,
  "status": Enum,            // ACTIVE, FULL, CANCELLED
  "createdAt": DateTime,
  "updatedAt": DateTime
}
```

**EventParticipant** (MongoDB Collection: `event_participants`)
```java
{
  "_id": ObjectId,           // MongoDB 내부 ID
  "participantId": Long,     // 비즈니스 키
  "eventId": Long,           // 인덱스
  "userId": Long,            // 인덱스
  "status": Enum,            // ACTIVE, WITHDRAWN
  "joinedAt": DateTime
}
```

</details>

<details>
<summary><strong>인덱스 전략</strong></summary>

- **텍스트 검색**: title, description, location
- **날짜 필터링**: meetingDateTime
- **상태 필터링**: status
- **복합 인덱스**: (eventId, userId) - 중복 참여 방지

</details>

## 🔒 핵심 원칙

<details>
<summary><strong>CQRS Query Side</strong></summary>

- **읽기 전용**: 직접적인 데이터 수정 금지
- **이벤트 기반**: Command Service 이벤트 구독을 통한 데이터 동기화
- **조회 최적화**: 복잡한 조회 쿼리와 집계 연산 지원
- **최종 일관성**: Eventually Consistent 모델

</details>

<details>
<summary><strong>성능 최적화</strong></summary>

- **MongoDB 활용**: 유연한 스키마와 빠른 조회 성능
- **텍스트 인덱스**: 전문 검색 지원
- **페이징**: 대용량 데이터 효율적 처리
- **비정규화**: 조회 성능을 위한 데이터 중복 허용

</details>

## 🛠️ 개발 현황

### ✅ 완료된 기능
- 기본 프로젝트 구조 설정
- MongoDB 연동 및 도메인 모델 정의
- DatingMeetingCreated 이벤트 핸들러 구현
- 기본 조회 API 구조 (Controller, Service, Repository)
- Kafka Consumer 설정

### ⚠️ 미완성 기능 (우선 수정 필요)
1. **이벤트 핸들러 완성**:
   - DatingMeetingUpdated 처리 로직
   - DatingMeetingDeleted 처리 로직  
   - ParticipantJoined 처리 로직
   - ParticipantLeft 처리 로직

2. **스키마 호환성 수정**:
   - userId 필드 타입 통일 (String vs Long)
   - 이벤트 필드명 통일 (eventId vs datingMeetingId)

3. **API 구현 완성**:
   - 사용자별 이벤트 조회 API
   - 고급 검색 및 필터링 기능

<details>
<summary><strong>🧪 테스트</strong></summary>

### 단위 테스트
```bash
./gradlew test
```

### 통합 테스트
- Embedded MongoDB를 사용한 Repository 테스트
- Kafka Test Containers를 사용한 이벤트 처리 테스트

</details>

<details>
<summary><strong>📄 API 문서</strong></summary>

- 개발 서버 실행 후 Swagger UI 접근 가능 (예정)
- MongoDB Compass를 통한 데이터 조회 가능

</details>

<details>
<summary><strong>🔍 모니터링</strong></summary>

- 이벤트 처리 성공/실패 로그
- MongoDB 쿼리 성능 모니터링
- Kafka Consumer Lag 모니터링

</details>

<details>
<summary><strong>🚨 주의사항</strong></summary>

- Command Service와의 이벤트 스키마 호환성 확인 필수
- 이벤트 중복 처리 방지 로직 구현 (멱등성 보장)
- MongoDB 인덱스 전략에 따른 쿼리 최적화 필요
- 대용량 데이터 처리 시 페이징 성능 고려

</details>