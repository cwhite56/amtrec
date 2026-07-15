package com.cwhite56.amtrec.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cwhite56.amtrec.domain.dtos.SpellListDto;
import com.cwhite56.amtrec.domain.dtos.UserDto;
import com.cwhite56.amtrec.services.UserService;

@RestController
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto ) {

        UserDto savedUser = userService.createUser(userDto);

        return new ResponseEntity<>((savedUser), HttpStatus.CREATED);
        
    }

    @PostMapping("/users/{id}/spelllists")
    public ResponseEntity<SpellListDto> createUpdateSpellList(@PathVariable("id") String username, @RequestBody SpellListDto spellListDto) {

        boolean doesUserExists = userService.userExists(username);

        if(!doesUserExists) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        SpellListDto savedSpellList = userService.createOrUpdateSpellList(username, spellListDto);

        return new ResponseEntity<>(savedSpellList, HttpStatus.CREATED);
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") String username) {

        boolean doesUserExist = userService.userExists(username);

        if(!doesUserExist) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        UserDto foundUser = userService.getUser(username);

        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}/spelllists/{title}")
    public ResponseEntity<SpellListDto> getSpellList(@PathVariable("id") String username, @PathVariable("title") String title) {

        boolean doesUserExist = userService.userExists(username);

        if(!doesUserExist) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        boolean doesSpellListExist = userService.spellListExists(title);

        if(!doesSpellListExist) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        SpellListDto foundSpellList = userService.getSpellList(username, title);


        return new ResponseEntity<>(foundSpellList, HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") String username) {

        userService.deleteUser(username);

        return new ResponseEntity<UserDto>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("users/{id}/spelllists/{title}")
    public ResponseEntity<SpellListDto> deleteSpellList(@PathVariable("id") String username, @PathVariable("title") String title) {

         boolean doesUserExist = userService.userExists(username);

        if(!doesUserExist) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        boolean doesSpellListExist = userService.spellListExists(title);

        if(!doesSpellListExist) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        userService.deleteSpellList(username, title);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
