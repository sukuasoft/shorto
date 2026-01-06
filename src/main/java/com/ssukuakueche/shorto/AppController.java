package com.ssukuakueche.shorto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class AppController {
    @GetMapping
    public HashMap<String, Object> getHello(){
        HashMap<String, Object> response = new HashMap<>();

        response.put("message", "Server is running");
        return response;
    }

}
