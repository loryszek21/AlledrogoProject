package com.example.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ContentController {
    @GetMapping("/AlledrogoProject/frontend/src/app/(site)/login/page.js")
    public String login(){
        return "login";
    }

    @GetMapping("/AlledrogoProject/frontend/src/app/(site)/signup/page.js")
    public String signup(){
        return "signup";
    }

    @GetMapping("/AlledrogoProject/frontend/src/app/(site)/home/page.js")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
}
