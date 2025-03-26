package com.gbnam.neulbom.service;

import com.gbnam.neulbom.entity.Notice;
import com.gbnam.neulbom.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    // 공지 추가
    public Notice createNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    // 전체 공지 조회
    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

    // 특정 공지 조회
    public Optional<Notice> getNoticeById(Long id) {
        return noticeRepository.findById(id);
    }

    // 공지 수정 (updatable한 필드: title, region, content)
    public Notice updateNotice(Long id, Notice updatedNotice) {
        return noticeRepository.findById(id).map(notice -> {
            notice.setTitle(updatedNotice.getTitle());
            notice.setContent(updatedNotice.getContent());
            notice.setRegion(updatedNotice.getRegion());
            // date, timestamp는 updatable=false 이므로 수정하지 않음.
            return noticeRepository.save(notice);
        }).orElseThrow(() -> new RuntimeException("Notice not found with id " + id));
    }

    // 특정 공지 삭제
    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }
}