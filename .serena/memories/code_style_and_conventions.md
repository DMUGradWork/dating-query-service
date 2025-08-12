# Code Style and Conventions

## Java 코딩 스타일
- **Java Version**: 21
- **Code Style**: Spring Boot 표준 컨벤션 준수
- **Lombok 사용**: `@Data`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor` 등 적극 활용

## 패키지 구조 (권장)
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
└── config/             # Configuration Classes
```

## 명명 규칙
- **클래스명**: PascalCase (예: `DatingEventController`)
- **메서드명**: camelCase (예: `findEventById`)
- **상수명**: UPPER_SNAKE_CASE (예: `MAX_PARTICIPANTS`)
- **패키지명**: 소문자, 점으로 구분 (예: `com.grewmeet.dating.datingqueryservice`)

## 아키텍처 가이드라인
- **EDA + CQRS 준수**: Query Side만 담당, 직접적인 데이터 수정 금지
- **읽기 전용 API**: 모든 엔드포인트는 GET 메서드만 사용
- **이벤트 기반**: 모든 데이터 변경은 Kafka 이벤트를 통해서만
- **최종 일관성**: Eventually Consistent 모델 수용