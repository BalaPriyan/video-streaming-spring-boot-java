package com.example.videos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.videos.model.Movie;
import com.example.videos.repository.MovieRepository;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movies")
@CrossOrigin // Allow frontend access
public class MovieController {

    private static final String VIDEO_DIR = "uploads/videos/";
    private static final String THUMBNAIL_DIR = "uploads/thumbnails/";

    @Autowired
    private MovieRepository movieRepository;

    @PostMapping("/upload")
    public Movie uploadMovie(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String genre,
            @RequestParam String language,
            @RequestParam("video") MultipartFile videoFile,
            @RequestParam("thumbnail") MultipartFile thumbnailFile
    ) throws IOException {

        // Create directories if not exist
        Files.createDirectories(Paths.get(VIDEO_DIR));
        Files.createDirectories(Paths.get(THUMBNAIL_DIR));

        // Save video
        String videoFileName = UUID.randomUUID() + "_" + videoFile.getOriginalFilename();
        Path videoPath = Paths.get(VIDEO_DIR + videoFileName);
        Files.copy(videoFile.getInputStream(), videoPath, StandardCopyOption.REPLACE_EXISTING);

        // Save thumbnail
        String thumbnailFileName = UUID.randomUUID() + "_" + thumbnailFile.getOriginalFilename();
        Path thumbnailPath = Paths.get(THUMBNAIL_DIR + thumbnailFileName);
        Files.copy(thumbnailFile.getInputStream(), thumbnailPath, StandardCopyOption.REPLACE_EXISTING);

        // Save movie metadata
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setGenre(genre);
        movie.setLanguage(language);
        movie.setVideoPath("/" + videoPath.toString().replace("\\", "/"));
        movie.setThumbnailPath("/" + thumbnailPath.toString().replace("\\", "/"));
        movie.setUploadedAt(LocalDateTime.now());

        return movieRepository.save(movie);
    }

    @GetMapping
    public List<Movie> listMovies() {
        return movieRepository.findAll();
    }
}

