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

    // 업로드 데이터 수정 (updatable한 필드: title, detail, type, link)
    public Upload updateUpload(Long id, Upload updatedUpload) {
        return uploadRepository.findById(id).map(upload -> {
            upload.setTitle(updatedUpload.getTitle());
            upload.setDetail(updatedUpload.getDetail());
            upload.setType(updatedUpload.getType());
            upload.setLink(updatedUpload.getLink());
            // timestamp는 updatable=false 이므로 수정하지 않음.
            return uploadRepository.save(upload);
        }).orElseThrow(() -> new RuntimeException("Upload not found with id " + id));
    }

    // 특정 업로드 데이터 삭제
    public void deleteUpload(Long id) {
        uploadRepository.deleteById(id);
    }
}