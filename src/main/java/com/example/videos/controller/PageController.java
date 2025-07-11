package com.example.videos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // renders templates/login.html
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register"; // renders templates/register.html
    }
}
