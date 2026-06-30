package com.cwhite56.amtrec.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwhite56.amtrec.domain.User;
import com.cwhite56.amtrec.domain.UserDto;
import com.cwhite56.amtrec.mappers.UserMapper;
import com.cwhite56.amtrec.services.UserService;

@RestController
@RequestMapping("/app/users")
public class UserController {
    
    private UserService userService;
    private UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto ) {
        
        User newUser = userMapper.mapFrom(userDto);

        userService.createUser(newUser);

        return new ResponseEntity<>(userMapper.mapTo(newUser), HttpStatus.CREATED);
    }
}
