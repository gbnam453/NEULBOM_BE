package com.gbnam.neulbom.controller;

import com.gbnam.neulbom.entity.Download;
import com.gbnam.neulbom.repository.DownloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/neulbom/api/download")
public class DownloadController {

    @Autowired
    private DownloadRepository downloadRepository;

    @GetMapping
    public List<Download> getDownloads() {
        return downloadRepository.findAll();
    }

    @PostMapping
    public Download addDownload(@RequestBody Download download) {
        return downloadRepository.save(download);
    }
}