package com.cwhite56.amtrec.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "home";
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

    @GetMapping("users/{id}/spell-lists/dashboard")
    public String userSpellLists(@PathVariable("id") String username, Model model) {
        
        List<SpellListDto> list = userService.getAllUsersSpellLists(username);

        model.addAttribute("username", username);

        model.addAttribute("dtoList", list);

        return "user-spell-lists";
    }

    @GetMapping("users/{id}/spell-lists")
    public String classSelection(@PathVariable("id") String username, Model model) {

        model.addAttribute("username", username);

        return "class-selection";
    }

    @PostMapping("users/{id}/spell-lists/builder")
    public String spellListBuilder(@PathVariable("id") String username, @RequestParam("classSelection") String classSelection, Model model) {


        model.addAttribute("username", username);
        model.addAttribute("newList", new SpellListDto());

        switch (classSelection) {
            case "wizard":
                
                return "wizard-builder";

            case "bard":

                return "bard-builder";

            case "healer":

                return "healer-builder";
            
            case "druid":

                return "druid-builder";
        
            default:
                return "home";
        }
    }

    
    
}
