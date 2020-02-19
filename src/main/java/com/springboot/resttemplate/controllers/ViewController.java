package com.springboot.resttemplate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String getPageLogin() {
        return "login";
    }

    @GetMapping("/home")
    public String getUserPage() {
        return "home";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }

    @GetMapping("/forbidden")
    public String getForbiddenPage() {
        return "error404";
    }
}
