package com.example.videos.controller;

import com.example.videos.model.Movie;
import com.example.videos.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PlayerController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/player/{id}")
    public String getPlayer(@PathVariable Long id, Model model) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie == null) {
            return "error/404"; // or a custom not-found page
        }
        model.addAttribute("movie", movie);
        return "player"; // maps to player.html
    }
}
