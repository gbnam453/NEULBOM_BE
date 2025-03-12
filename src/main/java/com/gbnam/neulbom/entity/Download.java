package com.gbnam.neulbom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Download")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Download {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 자동 증가 ID

    @Column(nullable = false)
    private String title; // 제목

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Region region; // 지역 ENUM

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category; // 카테고리 ENUM

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type; // 타입 ENUM

    @Column(nullable = false, columnDefinition = "TEXT")
    private String link; // 파일 다운로드 URL

    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timestamp; // 생성 시간

    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();
    }

    // ENUM 정의
    public enum Region {
        대전, 서산, 아산, 전라제주
    }

    public enum Category {
        공통, 기후환경, 문화예술, 사회정서, 창의과학, 체육
    }

    public enum Type {
        교안, 활동지, 영상
    }
}