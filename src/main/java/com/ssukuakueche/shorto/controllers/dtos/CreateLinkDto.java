package com.ssukuakueche.shorto.controllers.dtos;


public  class CreateLinkDto {
    private int userId;
    private String url;

    public int getUserId (){
        return userId;
    }

    public String getUrl (){
        return url;
    }


    public void setUrl(String url){
        this.url = url;
    }

    public  void setUserId(int userId){
        this.userId=userId;
    }

}
