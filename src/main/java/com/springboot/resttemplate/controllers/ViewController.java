package com.springboot.resttemplate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String getPageLogin(HttpServletRequest request, HttpServletResponse response) {
        return "login";
    }

    @GetMapping("/home")
    public String getUserPage(HttpServletRequest request, HttpServletResponse response) {
        return "home";
    }

    @GetMapping("/admin")
    public String getAdminPage(HttpServletRequest request, HttpServletResponse response) {
        return "admin";
    }
}
