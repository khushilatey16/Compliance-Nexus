package com.nexus.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "Welcome to Compliance Nexus";
    }

    @GetMapping("/secured")
    public String secured(){
        return "Welcome to Secured Compliance Nexus";
    }
}
