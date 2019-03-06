package com.sysc4806.project.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.lang.Override;

@Controller
public class RouteController  {

    @RequestMapping("/signin")
    public String routeSignIn() {
        return "/";
    }

    @RequestMapping("/home")
    public String routeHome() {
        return "/";
    }

//    @RequestMapping("/error")
//    public String handleError() {
//        return "/";
//    }

//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
}
