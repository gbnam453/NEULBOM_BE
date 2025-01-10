package com.gbnam.neulbom.repository;

import com.gbnam.neulbom.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}