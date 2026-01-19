package com.ssukuakueche.shorto.controllers;

import com.ssukuakueche.shorto.models.Link;
import com.ssukuakueche.shorto.models.User;
import com.ssukuakueche.shorto.repositories.LinkRepository;
import com.ssukuakueche.shorto.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LinkRepository linkRepository;

    @PostMapping
    public User create(@RequestBody User data) {
        return  userRepository.save(data);

    }

    @GetMapping
    public Iterable<User> getAll() {

        return userRepository.findAll();
    }


    @GetMapping("{id}")
    public Optional<User> get(@PathVariable int id) {
        return userRepository.findById(id);
    }


    @GetMapping("{id}/links")
    public Iterable<Link> getAllByUser(@PathVariable int id) {
        return linkRepository.findAllByUserId(id);
    }


    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
    userRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public User update(@RequestBody User user) {
        return userRepository.save(user);
    }
}
