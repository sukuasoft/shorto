package com.ssukuakueche.shorto;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("links")
public class LinkController {
    @GetMapping
    public ArrayList<HashMap<String, Object>>getAll(){
        ArrayList<Link> links = LinkRepository.getAll();

        ArrayList<HashMap<String, Object>> response = new ArrayList<>();

        for (Link l : links){
            response.add(l.toMap());
        }

        return response;
    }


    @GetMapping("{id}")
    public  HashMap<String, Object> get(@PathVariable int id){
        try{
            Link link = LinkRepository.get(id);
            return link.toMap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping
    public  HashMap<String, Object> create(@RequestBody CreateLinkDto data){
        try {
            User user = UserRepository.get(data.getUserId());
            String shorted = null;
            do {
                shorted = LinkRepository.generateShortUrl();
            }
            while (LinkRepository.hasShortUrl(shorted));


            Link link = new Link(LinkRepository.generateId(), user.id, data.getUrl(), shorted);

            LinkRepository.create(link);
            return link.toMap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PutMapping("{id}")
    public HashMap<String, Object> update(@PathVariable int id, @RequestBody UpdateLinkDto data){
        try {
            Link link = LinkRepository.update(id, data);
            return link.toMap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("{id}")
    public void delete (@PathVariable int id){
        try {
            LinkRepository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
