package com.gbnam.neulbom.service;

import com.gbnam.neulbom.entity.Notice;
import com.gbnam.neulbom.entity.NoticeImage;
import com.gbnam.neulbom.repository.NoticeImageRepository;
import com.gbnam.neulbom.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoticeImageService {

    private final NoticeRepository noticeRepository;
    private final NoticeImageRepository noticeImageRepository;

    // 실제 파일이 저장될 디렉토리 (프로젝트 루트 기준)
    private final String uploadDir = "NoticeUploadImage";

    /**
     * 여러 이미지를 업로드하고 DB에 저장
     */
    public List<NoticeImage> uploadImages(Long noticeId, List<MultipartFile> files) throws IOException {
        // 1. noticeId에 해당하는 공지사항 엔티티 조회
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new RuntimeException("Notice not found with id " + noticeId));

        // 파일 저장 경로 설정 및 디렉토리 생성
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        List<NoticeImage> savedImages = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;

            // 고유한 파일명을 생성 (UUID 이용)
            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                // 확장자를 소문자로 변환해서 추출 (.jpeg, .jpg, .png 모두 처리)
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
            }
            String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

            // 파일 저장
            Path targetLocation = uploadPath.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // 웹 접근 가능한 URL 생성 (WebConfig에서 /NoticeUploadImage/**로 매핑)
            String fileUrl = "http://gbnam453.iptime.org:2401/NoticeUploadImage/" + uniqueFileName;

            // DB에 저장할 엔티티 생성
            NoticeImage image = new NoticeImage();
            image.setNotice(notice);
            image.setImageUrl(fileUrl);

            NoticeImage savedImage = noticeImageRepository.save(image);
            savedImages.add(savedImage);
        }

        return savedImages;
    }

    /**
     * 특정 공지사항에 연결된 모든 이미지 조회
     */
    public List<NoticeImage> getImagesByNoticeId(Long noticeId) {
        return noticeImageRepository.findByNoticeId(noticeId);
    }

    /**
     * 이미지 삭제 기능 추가
     *
     * 주어진 imageId로 NoticeImage 엔티티를 조회한 후, 해당 파일을 파일 시스템에서 삭제하고
     * DB에서 해당 레코드를 삭제합니다.
     */
    public void deleteImage(Long imageId) throws IOException {
        NoticeImage image = noticeImageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("NoticeImage not found with id " + imageId));
        // DB에 저장된 URL에서 파일명 추출 (URL은 "http://gbnam453.iptime.org:2401/NoticeUploadImage/파일명" 형태)
        String fileUrl = image.getImageUrl();
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        Path filePath = uploadPath.resolve(fileName);
        // 파일이 존재하면 삭제
        Files.deleteIfExists(filePath);
        // DB에서 NoticeImage 레코드 삭제
        noticeImageRepository.delete(image);
    }
}