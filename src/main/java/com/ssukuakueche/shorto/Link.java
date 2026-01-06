package com.ssukuakueche.shorto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Link {
    public int id;
    public int userId;
    public String url;
    public String shorted;

    Link (int id, int userId, String url, String shorted){
        this.id = id;
        this.userId = userId;
        this.url = url;
        this.shorted = shorted;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != this.getClass()) return false;

        Link myLink = (Link) obj;

        return myLink.id == this.id;
    }

    public HashMap<String, Object> toMap (){
        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("id", this.id);
        map.put("userId", this.userId);
        map.put("url", this.url);
        map.put("shorted", this.shorted);

        return map;
    }
}


class LinkRepository {
    protected static ArrayList<Link> items = new ArrayList<Link>();

    protected static int lastIndex = 0;

    static int generateId(){
        lastIndex++;
        return lastIndex;
    }

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

    static  boolean hasShortUrl(String shorted){

        for (Link item : items) {
            if(item.shorted.equals(shorted)) return true;
        }

        return false;

    }

    public  static  int count (){
        return items.size();
    }

    public static ArrayList<Link> getAll (){
        return items;
    }


    public  static ArrayList<Link> getAllByUser(int userId) throws Exception{
        User user = UserRepository.get(userId);

        ArrayList<Link> links = new ArrayList<>();
        for (Link item: items){
            if(item.userId == user.id){
                links.add(item);
            }
        }

        return links;
    }

    public static  Link getByShorted  (String shorted) throws Exception {

        Link link  = null;

        for (Link item: items){
            if(item.shorted.equals(shorted)) link = item;
        }

        if(link == null)  throw new Exception("Link not found");

        return link;
    }

    public static Link get (int linkId) throws Exception{

        Link link = null;
        for (Link l: items){
            if(l.id == linkId){
                link = l;
            }
        }


        if(link == null){
            throw new Exception("Link not found");
        }

        return link;
    }


    public static void   create (Link link){
        items.add(link);
    }

    public  static Link update (int id, UpdateLinkDto data) throws  Exception {
        Link link  = get(id);

        link.url = data.getUrl();

        return link;
    }
    public  static void delete(int linkId) throws Exception {
        Link link = get(linkId);

        items.remove(link);
    }
}



class CreateLinkDto {
    private int userId;
    private String url;

    public String getUrl() {
        return url;
    }

    public int getUserId() {
        return userId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}


class UpdateLinkDto {
    private String url;

    public String getUrl() {
        return url;
    }
}