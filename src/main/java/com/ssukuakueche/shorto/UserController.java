package com.ssukuakueche.shorto;

import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("users")
public class UserController {

    @PostMapping
    public HashMap<String, Object> create(@RequestBody CreateUserDto createUserDto){

        User user = new User(UserRepository.generateId(), createUserDto.getName(), createUserDto.getEmail(),
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


    @GetMapping("{id}")
    public HashMap<String, Object> get (@PathVariable int id){
       try {
           User user = UserRepository.get(id);
           return user.toMap();
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }


    @GetMapping("{id}/links")
    public ArrayList<HashMap<String, Object>> getAllByUser (@PathVariable int id){
       try {
           ArrayList<Link> links = LinkRepository.getAllByUser(id);

           ArrayList<HashMap<String, Object>> response = new ArrayList<>();

           for (Link item : links){
               response.add(item.toMap());
           }

           return response;
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }



    @DeleteMapping("{id}")
    public  void delete (@PathVariable int id){
        try {
            UserRepository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("{id}")
    public  HashMap<String, Object> update (@PathVariable int id, @RequestBody UpdateUserDto data){
        try {
           User user = UserRepository.update(id, data);
           return user.toMap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
