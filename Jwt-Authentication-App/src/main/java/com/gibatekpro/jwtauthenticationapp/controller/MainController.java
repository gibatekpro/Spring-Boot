package com.gibatekpro.jwtauthenticationapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/unrestricted")
public class MainController {

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Unrestricted area";
    }


}
