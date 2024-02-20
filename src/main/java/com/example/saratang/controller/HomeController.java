package com.example.saratang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("api/users/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("api/users/signup")
    public String signupPage() {
        return "signup";
    }
}
