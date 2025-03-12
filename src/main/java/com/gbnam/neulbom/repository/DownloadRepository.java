package com.gbnam.neulbom.repository;

import com.gbnam.neulbom.entity.Download;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DownloadRepository extends JpaRepository<Download, Long> {

    // 특정 지역의 다운로드 목록 조회
    List<Download> findByRegion(Download.Region region);

    // 특정 카테고리의 다운로드 목록 조회
    List<Download> findByCategory(Download.Category category);

    // 특정 타입의 다운로드 목록 조회
    List<Download> findByType(Download.Type type);
}