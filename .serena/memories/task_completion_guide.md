# Task Completion Guide

## 작업 완료 시 체크리스트

### 1. 코드 작성 후 필수 검증
```bash
./gradlew test    # 테스트 실행
./gradlew check   # 모든 검증 태스크 실행
./gradlew build   # 전체 빌드 확인
```

### 2. Git 작업 순서
1. **main 브랜치에서 최신 코드 받기**
   ```bash
   git checkout main
   git pull origin main
   ```

2. **기능별 새 브랜치 생성**
   ```bash
   git checkout -b feature/브랜치명
   ```

3. **작업 후 커밋**
   ```bash
   git add .
   git commit -m "간결하고 명확한 커밋 메시지"
   ```

4. **PR 생성**
   ```bash
   git push origin feature/브랜치명
   # 이후 GitHub에서 PR 생성 또는 CLI 사용
   ```

5. **merge 확인 후 main으로 이동**
   ```bash
   git checkout main
   git pull origin main
   ```

### 3. 중요 원칙
- **AI 관련 커밋 메시지 금지**: "Claude Code로 생성됨" 등 언급 금지
- **실제 변경사항 설명**: 구체적이고 간결한 커밋 메시지 작성
- **토큰 절약**: Claude Code는 터미널 명령어 직접 실행하지 않음

### 4. CQRS + EDA 준수사항
- Query Side 전용: 데이터 직접 수정 금지
- 이벤트 기반 데이터 변경만 허용
- 읽기 최적화된 모델 설계