package com.explore.SpringSecurity.Controller;

import com.explore.SpringSecurity.Entity.users;
import com.explore.SpringSecurity.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public users registerUsers(@RequestBody users user){
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody users user){
        return userService.verifyUser(user);
    }



}
