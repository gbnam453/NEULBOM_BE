package com.gbnam.neulbom.service;

import com.gbnam.neulbom.entity.Download;
import com.gbnam.neulbom.repository.DownloadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DownloadService {
    private final DownloadRepository downloadRepository;

    public DownloadService(DownloadRepository downloadRepository) {
        this.downloadRepository = downloadRepository;
    }

    // 전체 다운로드 리스트 조회
    public List<Download> getAllDownloads() {
        return downloadRepository.findAll();
    }

    // ID로 단일 다운로드 조회
    public Optional<Download> getDownloadById(Long id) {
        return downloadRepository.findById(id);
    }

    // 다운로드 추가
    public Download addDownload(Download download) {
        return downloadRepository.save(download);
    }

    // 다운로드 수정
    public Download updateDownload(Long id, Download updatedDownload) {
        return downloadRepository.findById(id).map(download -> {
            download.setTitle(updatedDownload.getTitle());
            download.setRegion(updatedDownload.getRegion());
            download.setCategory(updatedDownload.getCategory());
            download.setType(updatedDownload.getType());
            download.setLink(updatedDownload.getLink());
            return downloadRepository.save(download);
        }).orElseThrow(() -> new RuntimeException("Download not found with id " + id));
    }

    // 다운로드 삭제
    public void deleteDownload(Long id) {
        downloadRepository.deleteById(id);
    }
}