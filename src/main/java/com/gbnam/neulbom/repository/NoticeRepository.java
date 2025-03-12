package com.gbnam.neulbom.repository;

import com.gbnam.neulbom.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findByRegion(Notice.Region region); // 특정 지역 조회
}