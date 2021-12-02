package com.louis.spring.oauth.client.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {
    @GetMapping("/user")
    public Object user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getDetails();
    }
}
