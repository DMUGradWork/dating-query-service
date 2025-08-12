# API Endpoints Specification

## 예상 REST API 엔드포인트

### 이벤트 조회 API
```
GET /events                              # 이벤트 목록 조회 (페이징, 필터링)
GET /events/{eventId}                    # 이벤트 상세 조회
GET /events/{eventId}/participants       # 이벤트 참여자 목록
GET /users/{userId}/events               # 사용자 참여 이벤트 목록
GET /events/search                       # 이벤트 검색 (키워드, 지역, 날짜)
```

### 비즈니스 규칙
- **참여자 제한**: 최소 2명, 최대 100명
- **중복 참여 방지**: 한 사용자는 같은 이벤트에 한 번만 참여 가능
- **실시간 정원 관리**: currentParticipants 실시간 업데이트
- **시간 제약**: 미팅 일시는 현재 시간 이후만 허용

### 상태 관리
- **이벤트 상태**: ACTIVE, FULL, CANCELLED
- **참여자 상태**: ACTIVE, WITHDRAWN

### 응답 형식
- JSON 형태의 응답
- 페이징: page, size, totalElements, totalPages 포함
- 에러 응답: 표준 HTTP 상태 코드 사용