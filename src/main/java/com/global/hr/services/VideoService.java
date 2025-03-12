package com.global.hr.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.global.hr.models.Video;
import com.global.hr.repositories.VideoRepository;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    private static final String UPLOAD_DIR = "uploads/";

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public void saveVideo(Video video, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            // إنشاء مجلد التحميل إذا لم يكن موجودًا
            Path uploadPath = (Path) Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // حفظ الملف في المجلد
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);

            // تعيين مسار الملف في الكيان
            video.setFilePath(filePath.toString());
        }

        videoRepository.save(video);
    }

    public Video getVideoById(Long id) {
        return videoRepository.findById(id).orElseThrow(() -> new RuntimeException("Video not found"));
    }
}
