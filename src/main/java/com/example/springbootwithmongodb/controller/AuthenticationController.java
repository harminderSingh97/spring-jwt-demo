package com.example.springbootwithmongodb.controller;

import com.example.springbootwithmongodb.jwt.AuthenticationService;
import com.example.springbootwithmongodb.jwt.JwtService;
import com.example.springbootwithmongodb.model.LoginResponse;
import com.example.springbootwithmongodb.model.LoginUserDto;
import com.example.springbootwithmongodb.model.RegisterUserDto;
import com.example.springbootwithmongodb.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/signup" )
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {

        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse
                .setToken(jwtToken);
        loginResponse
                .setExpiresIn(jwtService.getExpirationTime());


        return ResponseEntity.ok(loginResponse);
    }
}
