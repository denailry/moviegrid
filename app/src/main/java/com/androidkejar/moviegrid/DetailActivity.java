package com.androidkejar.moviegrid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidkejar.moviegrid.review.ReviewModel;
import com.androidkejar.moviegrid.review.ReviewResult;
import com.androidkejar.moviegrid.video.*;
import com.google.android.youtube.player.YouTubeIntents;
import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by denail on 5/31/2017.
 */

public class DetailActivity extends AppCompatActivity {
    String url;
    String title;
    String synopsis;
    String release;
    Double rating;
    String backdrop;
    Integer id;

    ImageButton ibTrailer, ibReview, ibFav;
    ImageView ivPoster, ivBackdrop;
    Context cntPoster, cntBackdrop;
    TextView tvTitle, tvSynopsis, tvRelease, tvRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        synopsis = intent.getStringExtra("synopsis");
        release = intent.getStringExtra("release");
        rating = intent.getDoubleExtra("rating", 0);
        backdrop = intent.getStringExtra("backdrop");
        id = intent.getIntExtra("id",0);

        ibFav = (ImageButton) findViewById(R.id.detail_favourite);
        ibReview = (ImageButton) findViewById(R.id.detail_review);
        ibTrailer = (ImageButton) findViewById(R.id.detail_trailer);
        ivPoster = (ImageView) findViewById(R.id.detail_poster);
        cntPoster = ivPoster.getContext();
        tvTitle = (TextView) findViewById(R.id.detail_title);
        tvSynopsis = (TextView) findViewById(R.id.detail_synopsis);
        tvRelease = (TextView) findViewById(R.id.detail_relase);
        tvRating = (TextView) findViewById(R.id.detail_rating);
        ivBackdrop = (ImageView) findViewById(R.id.detail_backdrop);
        cntBackdrop = ivBackdrop.getContext();

        List<MovieFavourite> movieSearch = new Select()
                .from(MovieFavourite.class)
                .where(MovieFavourite_Table.id.is(id))
                .queryList();
        if (movieSearch.size() == 0){
            ibFav.setImageResource(R.drawable.favourite_1);
        } else {
            ibFav.setImageResource(R.drawable.favourite_2);
        }
        Picasso.with(cntPoster).load(url).into(ivPoster);
        tvTitle.setText(title.toUpperCase());
        tvSynopsis.setText(synopsis);
        tvRelease.setText(release);
        tvRating.setText(String.valueOf(rating));
        Picasso.with(cntBackdrop).load(backdrop).into(ivBackdrop);

        View.OnClickListener clickListener = new View.OnClickListener() {
            public void onClick(View v) {
                if (v.equals(ibTrailer)) {
                    ApiServiceVideo service = ApiHelper.client().create(ApiServiceVideo.class);
                    Call<VideoModel> call = service.getVideo(id, "ee1e829f024a1f293e8dc4f2902da614");
                    call.enqueue(new Callback<VideoModel>() {
                        @Override
                        public void onResponse(Call<VideoModel> call, Response<VideoModel> response) {
                            String videoKey = response.body().getResults().get(0).getKey();
                            String url = "http://www.youtube.com/watch?v=" + videoKey;
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                        }
                        @Override
                        public void onFailure(Call<VideoModel> call, Throwable t) {
                            Toast.makeText(ibTrailer.getContext(),"Unable to Open Youtube",Toast.LENGTH_SHORT).show();
                        }
                    });
                }  else if (v.equals(ibReview)) {
                    Intent reviewIntent = new Intent(getApplicationContext(), ReviewActivity.class);
                    reviewIntent.putExtra("id",id);
                    startActivity(reviewIntent);
                }
            }
        };
        ibTrailer.setOnClickListener(clickListener);

        View.OnClickListener clickListener_2 = new View.OnClickListener() {
            public void onClick(View v) {
                if (v.equals(ibReview)) {
                    Intent reviewIntent = new Intent(getApplicationContext(), ReviewActivity.class);
                    reviewIntent.putExtra("id",id);
                    startActivity(reviewIntent);
                }
            }
        };
        ibReview.setOnClickListener(clickListener_2);

        View.OnClickListener clickListener_3 = new View.OnClickListener() {
            public void onClick(View v) {
                if (v.equals(ibFav)) {
                    List<MovieFavourite> movieSearch = new Select()
                            .from(MovieFavourite.class)
                            .where(MovieFavourite_Table.id.is(id))
                            .queryList();
                    if (movieSearch.size() == 0){
                        MovieFavourite movieFav = new MovieFavourite();
                        movieFav.setID(id);
                        movieFav.setTitle(title);
                        movieFav.setUrl(url);
                        movieFav.setBackdrop(backdrop);
                        movieFav.setRating(rating);
                        movieFav.setSynopsis(synopsis);
                        movieFav.setRelease(release);
                        movieFav.save();
                        ibFav.setImageResource(R.drawable.favourite_2);
                        Toast.makeText(getApplicationContext(), "Added to Collection",Toast.LENGTH_SHORT).show();
                    } else {
                        MovieFavourite movieFav = movieSearch.get(0);
                        movieFav.delete();
                        ibFav.setImageResource(R.drawable.favourite_1);
                        Toast.makeText(getApplicationContext(), "Removed to Collection",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
        ibFav.setOnClickListener(clickListener_3);
    }
}
