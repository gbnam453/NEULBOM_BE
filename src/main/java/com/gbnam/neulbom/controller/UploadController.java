package com.gbnam.neulbom.controller;

import com.gbnam.neulbom.entity.Upload;
import com.gbnam.neulbom.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:2402", "http://gbnam453.iptime.org:2402"}) // React 프론트엔드 요청 허용
@RestController
@RequestMapping("/api/uploads")
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    // 모든 업로드 데이터 조회
    @GetMapping
    public ResponseEntity<List<Upload>> getAllUploads() {
        return ResponseEntity.ok(uploadService.getAllUploads());
    }

    // 특정 업로드 데이터 조회
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Upload>> getUploadById(@PathVariable Long id) {
        return ResponseEntity.ok(uploadService.getUploadById(id));
    }

    // 업로드 데이터 추가
    @PostMapping
    public ResponseEntity<Upload> createUpload(@RequestBody Upload upload) {
        Upload savedUpload = uploadService.createUpload(upload);
        return ResponseEntity.ok(savedUpload);
    }

    // 특정 업로드 데이터 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUpload(@PathVariable Long id) {
        uploadService.deleteUpload(id);
        return ResponseEntity.noContent().build();
    }
}