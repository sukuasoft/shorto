package com.ssukuakueche.shorto;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    public int id;
    public  String name;
    public String email;
    public String password;

    User(int id, String name, String email, String password){
        this.id= id;
        this.name = name;
        this.email= email;
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != this.getClass()) return false;

        User myUser = (User) obj;

        return myUser.id == this.id;
    }


    public HashMap<String, Object> toMap (){
        final HashMap<String, Object> map = new HashMap<>();
        map.put("id", this.id);
        map.put("name", this.name);
        map.put("email", this.email);
        map.put("password", this.password);

        return map;
    }
}


class UserRepository {
    protected static ArrayList<User> items = new ArrayList<>();


   static  int count (){
       return items.size();
   }

    static void create (User user){
        items.add(user);
    }

    static ArrayList<User> getAll (){
        return  items;

    }
    static User get (int userId) throws Exception {


        User user = null;
        for (User item : items) {

            if (item.id == userId) {
                user = item;

            }

        }

        if(user == null){
            throw new Exception("User not found");
        }

        return user;

    }

    static void delete (int userId) throws Exception {
        User user = get(userId);

        items.remove(user);
    }
}


class  CreateUserDto {
    private String name;
    private String email;
    private   String password;

    public void setEmail(String email) {
        this.email = email;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
