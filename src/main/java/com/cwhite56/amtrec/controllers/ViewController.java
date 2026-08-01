package com.cwhite56.amtrec.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("users/{id}/spell-lists/builder")
    public String spellListBuilder(@PathVariable("id") String username, @RequestParam("classSelection") String classSelection, Model model) {

        model.addAttribute("username", username);
        
        SpellListDto spellListDto = SpellListDto.builder()
                .user(username)
                .casterClass(classSelection)
                .build();
        model.addAttribute("spelllist", spellListDto);

        System.out.println("Size is: " + spellListDto.getSpentPoints().size());

        switch (classSelection) {
            case "WIZARD":
                
                return "wizard-builder";

            case "BARD":

                return "bard-builder";

            case "HEALER":

                return "healer-builder";
            
            case "DRUID":

                return "druid-builder";
        
            default:
                return "home";
        }
    }

    @GetMapping("users/{id}/spell-lists/{title}")
    public String updateSpellList(@PathVariable("id") String username, @PathVariable("title") String title, Model model) {

        model.addAttribute("username", username);

        SpellListDto foundSpellList = userService.getSpellList(title);
        

        model.addAttribute("spelllist", foundSpellList);

        switch (foundSpellList.getCasterClass()) {
            case "WIZARD":
                
                return "wizard-builder";

            case "BARD":

                return "bard-builder";

            case "HEALER":

                return "healer-builder";
            
            case "DRUID":

                return "druid-builder";
        
            default:
                return "home";
        }
    }

    
    
}
