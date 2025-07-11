package com.example.videos.controller;

import com.example.videos.model.HeroSection;
import com.example.videos.model.Movie;
import com.example.videos.repository.HeroThumbRepository;
import com.example.videos.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private HeroThumbRepository heroRepo;

    @Autowired
    private MovieRepository movieRepo;

    @GetMapping("/home")
    public String homePage(Model model) {
        List<HeroSection> heroList = heroRepo.findAll();
        List<Movie> movieList = movieRepo.findAll();
        model.addAttribute("heroList", heroList);
        model.addAttribute("movieList", movieList);
        return "home";
    }
}
