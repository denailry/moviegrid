package com.androidkejar.moviegrid;

import com.androidkejar.moviegrid.popular.PopularModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by denail on 5/31/2017.
 */

public interface ApiServicePopular {
    @GET("movie/popular?")
    Call<PopularModel> getPopularMovie(@Query("api_key") String apiKey, @Query("page") Integer page);
}
