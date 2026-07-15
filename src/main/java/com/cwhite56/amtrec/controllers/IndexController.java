package com.cwhite56.amtrec.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class IndexController {
    
    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/register")
    public String getRegister() {
        return String.format("Please register");
    }
}
