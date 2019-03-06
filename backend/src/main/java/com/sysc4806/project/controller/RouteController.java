package com.sysc4806.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
