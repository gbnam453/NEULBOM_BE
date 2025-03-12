package com.gbnam.neulbom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 연속된 숫자 (기본키)

    @Column(nullable = false)
    private String title; // 공지 제목

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Region region; // 대전, 서산, 아산 (ENUM)

    @Column(nullable = false, updatable = false)
    private LocalDate date = LocalDate.now(); // 자동 날짜 (YYYY-MM-DD)

    @Column(nullable = false)
    private String content; // 공지 내용

    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp = LocalDateTime.now(); // 생성 시간 (TIMESTAMP)

    public enum Region {
        전체, 대전, 서산, 아산, 전라제주
    }
}