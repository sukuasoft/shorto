package com.ssukuakueche.shorto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;

@RestController
public class AppController {
    @GetMapping
    public HashMap<String, Object> getHello(){
        HashMap<String, Object> response = new HashMap<>();

        response.put("message", "Server is running");
        return response;
    }

    @GetMapping("{shorted}")
    public ResponseEntity<Void> redirectTo (@PathVariable String shorted){
       try {
           Link link = LinkRepository.getByShorted(shorted);
           return  ResponseEntity
                   .status(HttpStatus.FOUND)
                   .location(URI.create(link.url))
                   .build();
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }


}
