package com.gbnam.neulbom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "notice_image")
public class NoticeImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Notice 테이블과 다대일 관계 (외래키: notice_id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id", nullable = false)
    @JsonIgnore  // 이 필드를 직렬화하지 않음
    private Notice notice;

    // 업로드된 이미지 경로(혹은 URL)
    @Column(name = "image_url", length = 500, nullable = false)
    private String imageUrl;
}