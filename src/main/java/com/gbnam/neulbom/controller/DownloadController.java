package com.gbnam.neulbom.controller;

import com.gbnam.neulbom.entity.Download;
import com.gbnam.neulbom.service.DownloadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:2402", "http://gbnam453.iptime.org:2402"}) // CORS 설정
@RestController
@RequestMapping("/api/downloads")
public class DownloadController {

        private final DownloadService downloadService;

        public DownloadController(DownloadService downloadService) {
                this.downloadService = downloadService;
        }

        // ✅ 전체 다운로드 목록 조회 (GET)
        @GetMapping
        public ResponseEntity<List<Download>> getAllDownloads() {
                return ResponseEntity.ok(downloadService.getAllDownloads());
        }

        // ✅ ID로 다운로드 단건 조회 (GET)
        @GetMapping("/{id}")
        public ResponseEntity<Download> getDownloadById(@PathVariable Long id) {
                Optional<Download> download = downloadService.getDownloadById(id);
                return download.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        // ✅ 다운로드 추가 (POST)
        @PostMapping
        public ResponseEntity<Download> addDownload(@RequestBody Download download) {
                return ResponseEntity.ok(downloadService.addDownload(download));
        }

        // ✅ 다운로드 수정 (PUT)
        @PutMapping("/{id}")
        public ResponseEntity<Download> updateDownload(@PathVariable Long id, @RequestBody Download updatedDownload) {
                return ResponseEntity.ok(downloadService.updateDownload(id, updatedDownload));
        }

        // ✅ 다운로드 삭제 (DELETE)
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteDownload(@PathVariable Long id) {
                downloadService.deleteDownload(id);
                return ResponseEntity.noContent().build();
        }
}