package com.global.hr.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.global.hr.models.Video;
import com.global.hr.services.VideoService;

@Controller
public class VideoController {
    @Autowired
    private VideoService videoService;

    @GetMapping("/videos")
    public String viewVideos(Model model) {
        model.addAttribute("videos", videoService.getAllVideos());
        return "video-list";
    }

    @GetMapping("/uploadVideo")
    public String showUploadForm(Model model) {
        model.addAttribute("video", new Video());
        return "upload-video";
    }

    @PostMapping("/uploadVideo")
    public String uploadVideo(@ModelAttribute Video video, @RequestParam("file") MultipartFile file) throws IOException {
        videoService.saveVideo(video, file);
        return "redirect:/videos";
    }

    @GetMapping("/playVideo/{id}")
    public String playVideo(@PathVariable Long id, Model model) {
        Video video = videoService.getVideoById(id);
        model.addAttribute("video", video);
        return "play-video";
    }
}
