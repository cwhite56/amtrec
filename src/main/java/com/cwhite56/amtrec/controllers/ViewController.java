package com.cwhite56.amtrec.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cwhite56.amtrec.domain.dtos.NewUserRequest;
import com.cwhite56.amtrec.domain.dtos.SpellListDto;
import com.cwhite56.amtrec.services.UserService;



@Controller

public class ViewController {

    private final UserService userService;

    public ViewController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("newUserRequest", new NewUserRequest());
        return "register";
    }

    @GetMapping("/users/{id}")
    public String userDashboard(@PathVariable("id") String username, Model model) {
        
        model.addAttribute("username", username);

        return "user-dashboard";
    }

    @GetMapping("users/{id}/spelllists")
    public String userListOfSpellLists(@PathVariable("id") String username, Model model) {
        
        List<SpellListDto> list = userService.getAllUsersSpellLists(username);

        model.addAttribute("username", username);

        model.addAttribute("dtoList", list);

        return "user-spelllists";
    }

    
    
}
