package com.cwhite56.amtrec.controllers;

import com.cwhite56.amtrec.services.UserService;

public class UserController {
    
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
