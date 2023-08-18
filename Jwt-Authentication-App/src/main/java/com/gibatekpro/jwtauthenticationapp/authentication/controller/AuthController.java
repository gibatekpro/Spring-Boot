package com.gibatekpro.jwtauthenticationapp.authentication.controller;

import com.gibatekpro.jwtauthenticationapp.authentication.dto.UserDto;
import com.gibatekpro.jwtauthenticationapp.authentication.entity.User;
import com.gibatekpro.jwtauthenticationapp.authentication.exception.AuthException;
import com.gibatekpro.jwtauthenticationapp.authentication.response.UserResponse;
import com.gibatekpro.jwtauthenticationapp.authentication.service.AuthService;
import com.gibatekpro.jwtauthenticationapp.authentication.service.AuthServiceImpl;
import com.gibatekpro.jwtauthenticationapp.http.CustomHttpResponse;
import com.gibatekpro.jwtauthenticationapp.http.exception.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static java.time.LocalTime.now;

@RestController
@RequestMapping("/api/auth/v1")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody User userRequest){

        if (authService.findUserByEmail(userRequest.getEmail()).isPresent()) {
            throw new AuthException("User with this email already exists");
        }

        Optional<UserResponse> user = authService.register(userRequest);

        if (user.isPresent()) {
            return ResponseEntity.ok().body(
                    user.get());
        }

        throw new AuthException("User was not registered");

    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody User userRequest){

        Optional<UserResponse> user = authService.authenticate(userRequest);

        if (user.isPresent()) {
            return ResponseEntity.ok().body(
                    user.get());
        }

        throw new AuthException("Could not authenticate this user");

    }

}
