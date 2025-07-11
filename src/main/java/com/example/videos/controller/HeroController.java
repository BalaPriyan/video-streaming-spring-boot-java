package com.example.videos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.videos.model.HeroSection;
import com.example.videos.repository.HeroThumbRepository;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/herothumb")
@CrossOrigin // Allow frontend access
public class HeroController {

    private static final String THUMBNAIL_DIR = "uploads/hero-banner/";

    @Autowired
    private HeroThumbRepository HeroThumbRepository;

    @PostMapping("/upload-thumb")
    public HeroSection uploadhero(
            @RequestParam String title,
            @RequestParam("hero-section-banner") MultipartFile thumbnailFile
    ) throws IOException {


        Files.createDirectories(Paths.get(THUMBNAIL_DIR));

        // Save thumbnail
        String thumbnailFileName = UUID.randomUUID() + "_" + thumbnailFile.getOriginalFilename();
        Path thumbnailPath = Paths.get(THUMBNAIL_DIR + thumbnailFileName);
        Files.copy(thumbnailFile.getInputStream(), thumbnailPath, StandardCopyOption.REPLACE_EXISTING);

        // Save movie metadata
        HeroSection heroSection = new HeroSection();
        heroSection.setTitle(title);
        heroSection.setThumbnailPath("/" + thumbnailPath.toString().replace("\\", "/"));
        heroSection.setUploadedAt(LocalDateTime.now());

        return HeroThumbRepository.save(heroSection);
    }

    @GetMapping
    public List<HeroSection> listHero() {
        return HeroThumbRepository.findAll();
    }
}

