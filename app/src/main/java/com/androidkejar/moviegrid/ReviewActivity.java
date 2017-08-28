package com.androidkejar.moviegrid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.androidkejar.moviegrid.review.ReviewModel;
import com.androidkejar.moviegrid.review.ReviewResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity{

    Integer id;

    ArrayList<Review> listReview;
    ReviewAdapter reviewAdapater;
    RecyclerView rvReview;

    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        rvReview = (RecyclerView) findViewById(R.id.list_review);
        rvReview.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rvReview.setLayoutManager(llm);

        listReview = new ArrayList<>();
        reviewAdapater = new ReviewAdapter(listReview);
        rvReview.setAdapter(reviewAdapater);

        showReview();
    }
    private void showReview(){
        ApiServiceReview service = ApiHelper.client().create(ApiServiceReview.class);
        Call<ReviewModel> call = service.getReview(id, "ee1e829f024a1f293e8dc4f2902da614");
        call.enqueue(new Callback<ReviewModel>() {
            @Override
            public void onResponse(Call<ReviewModel> call, Response<ReviewModel> response) {
                List<ReviewResult> listReviewResult = response.body().getResults();
                for (ReviewResult reviewResult : listReviewResult) {
                    Review review = new Review();
                    review.setAuthor(reviewResult.getAuthor().toUpperCase());
                    review.setContent(reviewResult.getContent());

                    listReview.add(review);
                }

                reviewAdapater.refreshData(listReview);
            }
            public void onFailure(Call<ReviewModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Unable to Show Review",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}