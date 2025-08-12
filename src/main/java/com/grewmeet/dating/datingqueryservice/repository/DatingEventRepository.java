package com.grewmeet.dating.datingqueryservice.repository;

import com.grewmeet.dating.datingqueryservice.domain.DatingEvent;
import com.grewmeet.dating.datingqueryservice.domain.EventStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DatingEventRepository extends MongoRepository<DatingEvent, String> {
    
    // 이벤트 ID로 조회
    Optional<DatingEvent> findByEventId(Long eventId);
    
    // 상태별 조회 (페이징)
    Page<DatingEvent> findByStatus(EventStatus status, Pageable pageable);
    
    // 생성일 기준 내림차순 조회 (페이징)
    Page<DatingEvent> findAllByOrderByCreatedAtDesc(Pageable pageable);
    
    // 미래 이벤트만 조회 (현재 시간 이후)
    Page<DatingEvent> findByMeetingDateTimeAfterOrderByMeetingDateTimeAsc(
            LocalDateTime now, Pageable pageable);
    
    // 활성 상태의 미래 이벤트만 조회
    Page<DatingEvent> findByStatusAndMeetingDateTimeAfterOrderByMeetingDateTimeAsc(
            EventStatus status, LocalDateTime now, Pageable pageable);
    
    // 제목이나 설명에서 키워드 검색
    @Query("{ $or: [ " +
           "{ 'title': { $regex: ?0, $options: 'i' } }, " +
           "{ 'description': { $regex: ?0, $options: 'i' } }, " +
           "{ 'location': { $regex: ?0, $options: 'i' } } " +
           "] }")
    Page<DatingEvent> findByKeyword(String keyword, Pageable pageable);
}