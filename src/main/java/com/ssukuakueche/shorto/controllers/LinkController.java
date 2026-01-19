package com.ssukuakueche.shorto.controllers;

import com.ssukuakueche.shorto.controllers.dtos.CreateLinkDto;
import com.ssukuakueche.shorto.models.Link;
import com.ssukuakueche.shorto.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("links")
public class LinkController {

    @Autowired
    private LinkRepository linkRepository;

    @GetMapping
    public Iterable<Link> getAll(){
        return linkRepository.findAll();
    }


    @GetMapping("{id}")
    public Optional<Link> get(@PathVariable int id){
        return linkRepository.findById(id);
    }


    @PostMapping
    public  Link create(@RequestBody CreateLinkDto data){


            String shorted = null;
            do {
                shorted = LinkRepository.generateShortUrl();
            }
            while (linkRepository.findByShorted(shorted).isPresent());


            Link link = new Link();
            link.url = data.getUrl();
            link.userId = data.getUserId();
            link.shorted = shorted;

            return linkRepository.save(link);

    }


    @PutMapping("{id}")
    public Link update( @RequestBody Link data){
        return linkRepository.save(data);

    }

    @DeleteMapping("{id}")
    public void delete (@PathVariable int id){
        linkRepository.deleteById(id);
    }
}
