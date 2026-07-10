package com.cwhite56.amtrec.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class LoginController {
    
    //private final AuthenticationManager authenticationManager;

    private final AuthenticationSerivce authenticationService;

    public LoginController(AuthenticationSerivce authenticationSerivce) {
        this.authenticationService = authenticationSerivce;
    }

    @PostMapping("/users/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        /* 
        Authentication authenticationRequest = 
            UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());

        try {
            Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        catch(AuthenticationException e) {
            
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        */
       return new ResponseEntity<>(authenticationService.authenticate(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/users/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {

        return new ResponseEntity<>(authenticationService.register(registerRequest), HttpStatus.OK);
    }
}
