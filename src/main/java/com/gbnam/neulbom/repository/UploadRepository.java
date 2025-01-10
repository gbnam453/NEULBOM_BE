package com.gbnam.neulbom.repository;

import com.gbnam.neulbom.entity.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadRepository extends JpaRepository<Upload, Long> {
}