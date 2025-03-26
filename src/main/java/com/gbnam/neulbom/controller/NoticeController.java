package com.gbnam.neulbom.controller;

import com.gbnam.neulbom.entity.Notice;
import com.gbnam.neulbom.entity.NoticeImage;
import com.gbnam.neulbom.service.NoticeService;
import com.gbnam.neulbom.service.NoticeImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:2402", "http://gbnam453.iptime.org:2402"})
@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;
    private final NoticeImageService noticeImageService;

    // 공지 추가 (POST)
    @PostMapping
    public ResponseEntity<Notice> createNotice(@RequestBody Notice notice) {
        Notice savedNotice = noticeService.createNotice(notice);
        return ResponseEntity.ok(savedNotice);
    }

    // 전체 공지 조회 (GET)
    @GetMapping
    public ResponseEntity<List<Notice>> getAllNotices() {
        return ResponseEntity.ok(noticeService.getAllNotices());
    }

    // 특정 공지 조회 (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Notice>> getNoticeById(@PathVariable Long id) {
        return ResponseEntity.ok(noticeService.getNoticeById(id));
    }

    // 공지 수정 (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Notice> updateNotice(@PathVariable Long id, @RequestBody Notice updatedNotice) {
        return ResponseEntity.ok(noticeService.updateNotice(id, updatedNotice));
    }

    // 특정 공지 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return ResponseEntity.noContent().build();
    }

    // 공지사항에 이미지 업로드 (POST /api/notices/{noticeId}/images)
    @PostMapping("/{noticeId}/images")
    public ResponseEntity<?> uploadImages(
            @PathVariable Long noticeId,
            @RequestParam("files") List<MultipartFile> files
    ) {
        try {
            List<NoticeImage> savedImages = noticeImageService.uploadImages(noticeId, files);
            return ResponseEntity.ok(savedImages);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("이미지 업로드 중 오류 발생: " + e.getMessage());
        }
    }

    // 특정 공지에 연결된 이미지 조회 (GET /api/notices/{noticeId}/images)
    @GetMapping("/{noticeId}/images")
    public ResponseEntity<List<NoticeImage>> getImagesByNoticeId(@PathVariable Long noticeId) {
        List<NoticeImage> images = noticeImageService.getImagesByNoticeId(noticeId);
        return ResponseEntity.ok(images);
    }

    // 특정 공지에 연결된 이미지 삭제 (DELETE /api/notices/{noticeId}/images/{imageId})
    @DeleteMapping("/{noticeId}/images/{imageId}")
    public ResponseEntity<?> deleteNoticeImage(@PathVariable Long noticeId, @PathVariable Long imageId) {
        try {
            noticeImageService.deleteImage(imageId);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("이미지 삭제 중 오류 발생: " + e.getMessage());
        }
    }
}