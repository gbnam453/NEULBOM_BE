package com.gbnam.neulbom.controller;

import com.gbnam.neulbom.entity.Upload;
import com.gbnam.neulbom.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/neulbom/api/upload")
public class UploadController {

    @Autowired
    private UploadRepository uploadRepository;

    @GetMapping
    public List<Upload> getUploads() {
        return uploadRepository.findAll();
    }

    @PostMapping
    public Upload addUpload(@RequestBody Upload upload) {
        return uploadRepository.save(upload);
    }
}