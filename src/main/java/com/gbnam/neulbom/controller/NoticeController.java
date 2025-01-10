package com.gbnam.neulbom.controller;

import com.gbnam.neulbom.entity.Notice;
import com.gbnam.neulbom.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/neulbom/api/notice")
public class NoticeController {

    @Autowired
    private NoticeRepository noticeRepository;

    @GetMapping
    public List<Notice> getNotices() {
        return noticeRepository.findAll();
    }

    @PostMapping
    public Notice addNotice(@RequestBody Notice notice) {
        return noticeRepository.save(notice);
    }
}