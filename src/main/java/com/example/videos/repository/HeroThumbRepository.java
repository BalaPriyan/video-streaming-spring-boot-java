package com.example.videos.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.videos.model.HeroSection;

public interface HeroThumbRepository extends JpaRepository<HeroSection, Long> {}

