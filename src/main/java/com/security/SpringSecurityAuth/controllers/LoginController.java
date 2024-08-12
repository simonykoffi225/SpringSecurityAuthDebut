package com.security.SpringSecurityAuth.controllers;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/user")
    public String getUser() {
        return "Bienvenue, user";
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "Bienvenue, admin";
    }

    @GetMapping("/info")
    public String getUserInfo(Principal user) {
        if (user instanceof UsernamePasswordAuthenticationToken) {
            return "Authenticated as: " + user.getName();
        }
        return "NA";
    }
}
