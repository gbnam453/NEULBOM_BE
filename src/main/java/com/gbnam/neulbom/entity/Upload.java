package com.gbnam.neulbom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Upload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 연속된 숫자 (기본키)

    @Column(nullable = false)
    private String title; // 제목

    @Column(nullable = false, columnDefinition = "TEXT")
    private String detail; // 상세 내용

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UploadType type; // survey 또는 file

    @Column(nullable = false, columnDefinition = "TEXT")
    private String link; // 파일 URL 또는 설문 링크

    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp = LocalDateTime.now(); // 생성 시간 (자동 입력)

    public enum UploadType {
        survey, file
    }
}