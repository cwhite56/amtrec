package com.cwhite56.amtrec.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwhite56.amtrec.domain.dtos.SpellListDto;
import com.cwhite56.amtrec.domain.dtos.UserDto;
import com.cwhite56.amtrec.domain.entities.SpellList;
import com.cwhite56.amtrec.domain.entities.User;
import com.cwhite56.amtrec.mappers.SpellListMapper;
import com.cwhite56.amtrec.mappers.UserMapper;
import com.cwhite56.amtrec.services.UserService;

@RestController
@RequestMapping("/app")
public class UserController {
    
    private UserService userService;
    private UserMapper userMapper;

    private SpellListMapper spellListMapper;

    public UserController(UserService userService, UserMapper userMapper, SpellListMapper spellListMapper) {
        this.userService = userService;
        this.userMapper = userMapper;

        this.spellListMapper = spellListMapper;
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto ) {

        User newUser = userMapper.mapFrom(userDto);

        User savedNewUser = userService.createUser(newUser);

        return new ResponseEntity<>(userMapper.mapTo(savedNewUser), HttpStatus.CREATED);
        
    }

    @PostMapping("/users/{id}/spelllists")
    public ResponseEntity<UserDto> createSpellList(@PathVariable("id") String username, @RequestBody SpellListDto spellListDto) {

        SpellList newSpellList = spellListMapper.mapFrom(spellListDto);

        Optional<User> foundUser = userService.getUser(username);

        if(!foundUser.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User updatedUser = userService.createSpellList(foundUser, newSpellList);

        return new ResponseEntity<>(userMapper.mapTo(updatedUser), HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") String username) {

        Optional<User> foundUser = userService.getUser(username);

        return foundUser.map(user -> {
            UserDto userDto = userMapper.mapTo(user);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {

        List<User> users = userService.getAllUsers();

        return users.stream()
            .map(userMapper::mapTo)
            .toList();
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") String username) {

        userService.deleteUser(username);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
