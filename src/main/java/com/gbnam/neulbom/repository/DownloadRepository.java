package com.gbnam.neulbom.repository;

import com.gbnam.neulbom.entity.Download;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DownloadRepository extends JpaRepository<Download, Long> {
}