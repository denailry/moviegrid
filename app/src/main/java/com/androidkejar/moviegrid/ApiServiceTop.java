package com.androidkejar.moviegrid;

import com.androidkejar.moviegrid.popular.PopularModel;
import com.androidkejar.moviegrid.review.ReviewModel;
import com.androidkejar.moviegrid.top.TopModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by denail on 5/31/2017.
 */

public interface ApiServiceTop {
    @GET("movie/top_rated?")
    Call<TopModel> getTop(@Query("page") Integer page, @Query("api_key") String apiKey);
}

