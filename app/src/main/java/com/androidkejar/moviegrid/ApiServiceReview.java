package com.androidkejar.moviegrid;

import com.androidkejar.moviegrid.popular.PopularModel;
import com.androidkejar.moviegrid.review.ReviewModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by denail on 5/31/2017.
 */

public interface ApiServiceReview {
    @GET("movie/{movie_id}/reviews?")
    Call<ReviewModel> getReview(@Path("movie_id") Integer movieID, @Query("api_key") String apiKey);
}
