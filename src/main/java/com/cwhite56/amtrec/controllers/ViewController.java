package com.cwhite56.amtrec.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cwhite56.amtrec.domain.dtos.NewUserRequest;



@Controller

public class ViewController {
    
    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("newUserRequest", new NewUserRequest());
        return "register";
    }

    
    
}
