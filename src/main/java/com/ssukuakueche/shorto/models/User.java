package com.ssukuakueche.shorto.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public  String name;
    public String email;
    public String password;
}