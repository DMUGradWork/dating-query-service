# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 프로젝트 개요

이것은 **Dating Query Service** - 데이팅 이벤트의 읽기 작업을 담당하는 CQRS Query-side 마이크로서비스입니다. Spring Boot 3.5.4와 Java 21로 구축되었으며, `com.grewmeet.dating` 패키지 구조 하에 있는 EDA(Event-Driven Architecture) + CQRS 시스템의 일부입니다.

## 핵심 책임

나는 다음을 담당하는 **Query Service**입니다:
- Dating Command Service에서 Kafka를 통해 발행하는 이벤트 구독
- 데이팅 이벤트를 위한 최적화된 Read Model 유지
- 데이팅 이벤트 데이터에 대한 빠르고 효율적인 조회 API 제공
- **데이터를 직접 수정하지 않음** - 오직 이벤트 구독을 통해서만

## 개발 명령어

### 빌드 및 실행
```bash
./gradlew build          # 프로젝트 빌드 및 테스트 실행
./gradlew bootRun        # Spring Boot 애플리케이션 실행
./gradlew bootTestRun    # 테스트 런타임 클래스패스로 실행
./gradlew clean          # 빌드 디렉토리 정리
```

### 테스트
```bash
./gradlew test           # JUnit Platform을 사용하여 모든 테스트 실행
./gradlew check          # 모든 검증 태스크 실행
```

### 빌드 아티팩트
```bash
./gradlew bootJar        # 의존성 포함한 실행 가능한 JAR 생성
./gradlew bootBuildImage # OCI 컨테이너 이미지 빌드
```

**중요**: 토큰 절약을 위해 Claude Code가 직접 터미널 명령어를 실행하지 않습니다. 대신 위 명령어들을 사용자에게 알려드리니 직접 실행해 주세요.

## 아키텍처

### 기술 스택
- **프레임워크**: Spring Boot 3.5.4
- **Java 버전**: 21
- **빌드 도구**: Gradle with Wrapper
- **데이터베이스**: H2 (개발용) → PostgreSQL/MySQL (운영용)
- **메시지 큐**: Apache Kafka (이벤트 구독용)
- **아키텍처**: EDA (Event-Driven Architecture) + CQRS (Command Query Responsibility Segregation)
- **의존성**: Spring Web, Lombok, Spring Boot DevTools, Spring Kafka

### 예상 API 엔드포인트
```
GET /events                              # 이벤트 목록 조회 (페이징, 필터링)
GET /events/{eventId}                    # 이벤트 상세 조회
GET /events/{eventId}/participants       # 이벤트 참여자 목록
GET /users/{userId}/events               # 사용자 참여 이벤트 목록
GET /events/search                       # 이벤트 검색 (키워드, 지역, 날짜)
```

### Kafka 이벤트 구독

Command Service에서 Kafka를 통해 다음 이벤트들을 구독해야 함:

**DatingMeetingCreated**
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

**DatingMeetingUpdated**
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

**DatingMeetingDeleted**
```json
{
  "datingMeetingId": Long,
  "participantIds": List<Long>,
  "deletedAt": LocalDateTime
}
```

**ParticipantJoined**
```json
{
  "datingMeetingId": Long,
  "participantId": Long,
  "userId": Long,
  "joinedAt": LocalDateTime
}
```

**ParticipantLeft**
```json
{
  "datingMeetingId": Long,
  "participantId": Long,
  "userId": Long,
  "leftAt": LocalDateTime
}
```

### 도메인 모델 (Read Model)

**DatingEvent** (조회에 최적화됨)
- Long id
- String title (최대 200자)
- String description (최대 1000자)
- LocalDateTime meetingDateTime
- String location (최대 300자)
- Integer maxParticipants (2-100명)
- Integer currentParticipants (실시간 참여자 수)
- String status (ACTIVE, FULL, CANCELLED)
- LocalDateTime createdAt, updatedAt

**EventParticipant**
- Long id
- Long eventId
- Long userId
- String status (ACTIVE, WITHDRAWN)
- LocalDateTime joinedAt

### 프로젝트 구조 (권장)
```
src/main/java/com/grewmeet/dating/datingqueryservice/
├── controller/          # REST API Controllers
├── dto/
│   ├── request/         # 조회 요청 DTO
│   └── response/        # 조회 응답 DTO
├── domain/             # Read Model Entities
├── repository/         # Data Access Layer
├── service/            # Business Logic Layer
├── event/              # Kafka Event Handlers
│   ├── handler/        # 각 이벤트별 핸들러
│   ├── consumer/       # Kafka Consumer
│   └── dto/           # 이벤트 DTO
└── config/             # Configuration Classes (Kafka Config 포함)
```

## 구현 가이드라인

### EDA + CQRS 원칙
- **Command와 Query 분리**: 이 서비스는 Query Side만 담당, Command는 별도 서비스
- **이벤트 기반**: 모든 데이터 변경은 Kafka 이벤트를 통해 전파
- **읽기 전용**: API 호출을 통한 직접적인 데이터 수정 금지
- **최종 일관성**: Eventually Consistent 모델 수용
- **조회 최적화**: 빠른 읽기 작업을 위한 비정규화된 Read Model 설계

### Kafka 설정 고려사항
- Consumer Group: 각 이벤트 타입별 독립적인 컨슈머 그룹
- Offset Management: 수동 커밋으로 정확한 이벤트 처리 보장
- Error Handling: 실패한 이벤트에 대한 재시도 및 DLQ 처리
- Serialization: JSON 기반 직렬화/역직렬화

### 비즈니스 규칙
- 참여자 제한: 최소 2명, 최대 100명
- 중복 참여 방지: 한 사용자는 같은 이벤트에 한 번만 참여 가능
- 정원 관리: currentParticipants 실시간 업데이트
- 시간 제약: 미팅 일시는 현재 시간 이후만 허용

### 에러 처리
- 멱등성: 동일 이벤트 중복 처리 방지 (이벤트 ID 기반)
- 재시도 로직: Kafka 이벤트 처리 실패 시 재시도
- 데드 레터 큐: 반복 실패 이벤트 처리

## 개발 방식 (중요)

### 작업 순서
- 기능 단위로 아래 순서로 작업한다.
  1. (main 브랜치에서 받아올 내용이 있다면) pull 하고 테스트 코드 실행
  2. 기능에 맞는 새로운 브랜치 생성
  3. 브랜치 변경 후 작업
  4. 작업 후 커밋
  5. 커밋 후 pr(git cli가 없다면 수동으로 명령어와 메시지를 안내할 것)
  6. merge가 되었는지 확인 후 main으로 변경


### 토큰 절약 정책
- Claude Code는 터미널 명령어를 직접 실행하지 않음
- 필요한 명령어를 사용자에게 알려주면 사용자가 직접 실행
- 빌드, 테스트, 실행 등의 명령어는 위의 "개발 명령어" 섹션 참고

### Git 컨벤션
- 커밋 메시지에 "Claude Code로 생성됨" 또는 유사한 AI 관련 메시지 추가 금지
- 커밋 메시지는 간결하고 실제 변경사항을 설명하도록 작성