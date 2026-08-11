package com.cwhite56.amtrec.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cwhite56.amtrec.domain.dtos.ClassStatsRequest;
import com.cwhite56.amtrec.domain.dtos.ClassStatsResponse;
import com.cwhite56.amtrec.services.UserService;

@RestController
@RequestMapping("/api/v1")
public class StatsController {

    private final UserService userService;

    public StatsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/spell-lists/global-stats")
    public ResponseEntity<ClassStatsResponse> getGlobalStats(@RequestBody ClassStatsRequest request) {

        ClassStatsResponse res = userService.getGlobalStats(request.getCasterClass(), request.getKingdom(), request.getSpellsPurchased());

        return new ResponseEntity<>(res, HttpStatus.OK);

    }
    
}
