package com.androidkejar.moviegrid;

import com.androidkejar.moviegrid.popular.PopularModel;
import com.androidkejar.moviegrid.video.VideoModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by denail on 5/31/2017.
 */

public interface ApiServiceVideo {
    @GET("movie/{movie_id}/videos?")
    Call<VideoModel> getVideo(@Path("movie_id") Integer movieID, @Query("api_key") String apiKey);
}
