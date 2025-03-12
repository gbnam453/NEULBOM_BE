package com.gbnam.neulbom.service;

import com.gbnam.neulbom.entity.Upload;
import com.gbnam.neulbom.repository.UploadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UploadService {

    private final UploadRepository uploadRepository;

    // 업로드 데이터 추가
    public Upload createUpload(Upload upload) {
        return uploadRepository.save(upload);
    }

    // 모든 업로드 데이터 조회
    public List<Upload> getAllUploads() {
        return uploadRepository.findAll();
    }

    // 특정 업로드 데이터 조회
    public Optional<Upload> getUploadById(Long id) {
        return uploadRepository.findById(id);
    }

    // 특정 업로드 데이터 삭제
    public void deleteUpload(Long id) {
        uploadRepository.deleteById(id);
    }
}