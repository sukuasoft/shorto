package com.ssukuakueche.shorto.repositories;


import com.ssukuakueche.shorto.models.Link;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Random;

@Repository
public interface LinkRepository extends CrudRepository<Link, Integer> {


    static  String generateShortUrl (){
        final String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random random = new Random();
        String shortUrl = "";

        for (int x = 0; x < 10; x++){
            int randomValue =  random.nextInt(letters.length());
            shortUrl += letters.charAt(randomValue);
        }

        return shortUrl;
    }


    public Optional<Link> findByShorted(String shorted);
    public  Iterable<Link> findAllByUserId(int userId);

}
