# Suggested Commands

## 빌드 및 실행
```bash
./gradlew build          # 프로젝트 빌드 및 테스트 실행
./gradlew bootRun        # Spring Boot 애플리케이션 실행
./gradlew clean          # 빌드 디렉토리 정리
```

## 테스트
```bash
./gradlew test           # JUnit Platform을 사용하여 모든 테스트 실행
./gradlew check          # 모든 검증 태스크 실행
```

## 빌드 아티팩트
```bash
./gradlew bootJar        # 의존성 포함한 실행 가능한 JAR 생성
./gradlew bootBuildImage # OCI 컨테이너 이미지 빌드
```

## Git 관련
```bash
git checkout -b feature/new-feature  # 새 기능 브랜치 생성
git add .                           # 변경사항 스테이징
git commit -m "Add new feature"     # 커밋
git push origin feature/new-feature # 브랜치 푸시
```

## macOS 유틸리티 명령어
```bash
ls -la      # 파일 목록 상세 보기
grep -r     # 재귀적 텍스트 검색
find        # 파일 검색
cd          # 디렉토리 이동
```

## 중요사항
- **토큰 절약 정책**: Claude Code는 터미널 명령어를 직접 실행하지 않음
- 필요한 명령어를 사용자에게 알려주면 사용자가 직접 실행
- 커밋 메시지에 AI 관련 언급 금지