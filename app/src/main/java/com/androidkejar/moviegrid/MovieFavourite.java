package com.androidkejar.moviegrid;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by denail on 6/2/2017.
 */

@Table(database = MyDatabase.class)
public class MovieFavourite extends BaseModel {
    @Column
    @PrimaryKey
    Integer id;
    @Column
    String url;
    @Column
    String title;
    @Column
    String synopsis;
    @Column
    String release;
    @Column
    Double rating;
    @Column
    String backdrop;

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
    public String getUrl(){
        return url;
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
    public void setUrl(String url){
        this.url = url;
    }
}
