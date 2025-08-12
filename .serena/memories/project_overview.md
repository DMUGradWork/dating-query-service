# Dating Query Service 프로젝트 개요

## 목적
Dating Query Service는 CQRS 아키텍처의 **Query Side**를 담당하는 마이크로서비스입니다. 데이팅 이벤트의 읽기 작업에 특화되어 있으며, EDA(Event-Driven Architecture) + CQRS 시스템의 일부입니다.

## 핵심 책임
- Dating Command Service에서 Kafka를 통해 발행하는 이벤트 구독
- 데이팅 이벤트를 위한 최적화된 Read Model 유지
- 데이팅 이벤트 데이터에 대한 빠르고 효율적인 조회 API 제공
- **데이터 직접 수정 금지** - 오직 이벤트 구독을 통해서만 데이터 변경

## 기술 스택
- **프레임워크**: Spring Boot 3.5.4
- **Java 버전**: 21
- **빌드 도구**: Gradle with Wrapper
- **데이터베이스**: H2 (개발용) → PostgreSQL/MySQL (운영용)
- **메시지 큐**: Apache Kafka (이벤트 구독용)
- **의존성**: Spring Web, Lombok, Spring Boot DevTools, H2 Database
- **아키텍처 패턴**: EDA + CQRS

## 패키지 구조
`com.grewmeet.dating.datingqueryservice`