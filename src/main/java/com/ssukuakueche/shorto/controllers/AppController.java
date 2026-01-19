package com.ssukuakueche.shorto.controllers;


import com.ssukuakueche.shorto.models.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;
import java.util.Optional;

@RestController
public class AppController {

    @Autowired
    private com.ssukuakueche.shorto.repositories.LinkRepository linkRepository;

    @GetMapping
    public HashMap<String, Object> getHello(){
        HashMap<String, Object> response = new HashMap<>();

        response.put("message", "Server is running");
        return response;
    }

    @GetMapping("{shorted}")
    public ResponseEntity<Void> redirectTo (@PathVariable String shorted){

        Optional<Link> link = linkRepository.findByShorted(shorted);

        if (link.isEmpty()){
            return ResponseEntity.status(404).build();
        }


        return  ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(link.get().url))
                .build();

    }

}