package com.in28minutes.springboot.web.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean isValidUser(String userId, String password) {
        return userId.equalsIgnoreCase("Yaser") &&
                password.equalsIgnoreCase("dummy");
    }
}
