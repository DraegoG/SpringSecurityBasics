package com.draego.SpringSecurityBasics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class URLMappings {
    @GetMapping("/")
    public String homeUrl(){
        return "<h2>Hola<h2>";
    }

    @GetMapping("/user1")
    public String user1(){
        return "<h3>Welcome User<h3>";
    }

    @GetMapping("/admin")
    public String admin(){
        return "<h3>Welcome Admin<h3>";
    }
}
