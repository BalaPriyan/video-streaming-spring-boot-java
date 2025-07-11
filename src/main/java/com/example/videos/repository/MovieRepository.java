package com.example.videos.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.videos.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {}

