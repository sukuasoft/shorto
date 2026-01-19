package com.ssukuakueche.shorto.models;

import jakarta.persistence.*;


@Entity
@Table(name = "links")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public int userId;
    public String url;
    public String shorted;

}