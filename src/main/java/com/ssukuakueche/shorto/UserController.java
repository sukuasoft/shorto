package com.ssukuakueche.shorto;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("users")
public class UserController {

    @PostMapping
    public HashMap<String, Object> create(@RequestBody CreateUserDto createUserDto){

        User user = new User(UserRepository.count(), createUserDto.getName(), createUserDto.getEmail(),
                createUserDto.getPassword());

        UserRepository.create(user);


        return user.toMap();

    }

    @GetMapping

    public ArrayList<HashMap<String, Object>> getAll(){

        ArrayList<User> users = UserRepository.getAll();

        ArrayList<HashMap<String, Object>> response = new ArrayList<>();

        for(User user: users) {
            response.add(user.toMap());
        }

        return response;

    }

    @DeleteMapping("{id}")
    public  void delete (@PathVariable int id){
        try {
            UserRepository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
