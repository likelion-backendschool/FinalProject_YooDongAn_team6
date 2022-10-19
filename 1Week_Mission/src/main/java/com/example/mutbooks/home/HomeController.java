package com.example.mutbooks.home;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "로그인 성공";
    }
}
