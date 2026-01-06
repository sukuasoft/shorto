package com.ssukuakueche.shorto;

import java.util.ArrayList;
import java.util.HashMap;

public class Link {
    public int id;
    public String userId;
    public String original;
    public String shorted;

    Link (int id, String userId, String original, String shorted){
        this.id = id;
        this.userId = userId;
        this.original = original;
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
        map.put("original", this.original);
        map.put("shorted", this.shorted);

        return map;
    }
}


class LinkRepository {
    protected static ArrayList<Link> items = new ArrayList<Link>();

    public static ArrayList<Link> getAll (){
        return items;
    }

    public static Link get (int linkId) throws Exception{

        Link link = null;
        for (Link l: items){
            if(l.id == linkId){
                link = l;
            }
        }


        if(link == null){
            throw new Exception("User not found");
        }

        return link;
    }

    public  static void delete(int linkId) throws Exception {
        Link link = get(linkId);

        items.remove(link);
    }
}
