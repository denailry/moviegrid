package com.androidkejar.moviegrid;

import java.io.Serializable;

/**
 * Created by denail on 5/31/2017.
 */

public class MovieDetails {
    String title;
    String synopsis;
    Double rating;
    String release;
    String backdrop;
    Integer id;

    public MovieDetails(){}

    public String getTitle(){
        return title;
    }
    public String getSynopsis(){
        return synopsis;
    }
    public Double getRating(){
        return rating;
    }
    public String getRelease(){
        return release;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setSynopsis(String synopsis){
        this.synopsis = synopsis;
    }
    public void setRating(Double rating){
        this.rating = rating;
    }
    public void setRelease(String release){
        this.release = release;
    }
    public String getBackdrop(){
        return backdrop;
    }
    public void setBackdrop(String backdrop){
        this.backdrop = backdrop;
    }
    public Integer getID(){
        return id;
    }
    public void setID(Integer id){
        this.id = id;
    }
}
