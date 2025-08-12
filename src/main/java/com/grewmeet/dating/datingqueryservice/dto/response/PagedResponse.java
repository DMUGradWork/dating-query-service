package com.grewmeet.dating.datingqueryservice.dto.response;

import org.springframework.data.domain.Page;
import java.util.List;

public record PagedResponse<T>(
    List<T> content,
    int page,
    int size,
    long totalElements,
    int totalPages,
    boolean first,
    boolean last
) {
    
    /**
     * Spring Data의 Page 객체로부터 PagedResponse 생성
     * @param page Spring Data Page 객체
     * @param content 변환된 컨텐츠 리스트
     * @return PagedResponse 인스턴스
     */
    public static <T> PagedResponse<T> from(Page<?> page, List<T> content) {
        return new PagedResponse<>(
            content,
            page.getNumber(),
            page.getSize(),
            page.getTotalElements(),
            page.getTotalPages(),
            page.isFirst(),
            page.isLast()
        );
    }
}