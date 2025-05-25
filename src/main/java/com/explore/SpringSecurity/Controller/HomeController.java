package com.explore.SpringSecurity.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String gethome(HttpServletRequest request){
        return "This is the home page and the session ID of this session is " + request.getSession().getId();
    }

}
