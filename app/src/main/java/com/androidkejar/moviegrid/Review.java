package com.androidkejar.moviegrid;

/**
 * Created by denail on 5/31/2017.
 */

public class Review {
    String author;
    String content;

    public Review(){}

    public void setAuthor(String author){
        this.author = author;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getAuthor(){
        return author;
    }
    public String getContent(){
        return content;
    }
}
