package com.sysc4806.project.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ArticleController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
