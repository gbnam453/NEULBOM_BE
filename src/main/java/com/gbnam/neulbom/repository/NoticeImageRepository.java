package com.gbnam.neulbom.repository;

import com.gbnam.neulbom.entity.NoticeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeImageRepository extends JpaRepository<NoticeImage, Long> {
    // 특정 공지사항에 연결된 모든 이미지 조회
    List<NoticeImage> findByNoticeId(Long noticeId);
}