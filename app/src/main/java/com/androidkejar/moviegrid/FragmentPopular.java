package com.androidkejar.moviegrid;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.androidkejar.moviegrid.popular.PopularModel;
import com.androidkejar.moviegrid.popular.Result;
import com.androidkejar.moviegrid.video.Key;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by denail on 6/2/2017.
 */

public class FragmentPopular extends Fragment {

    ArrayList<String> listURL;
    ImageAdapter imageAdapter;
    ArrayList<MovieDetails> listMovie;
    Integer page;

    public FragmentPopular() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_popular, container, false);

        GridView gridview = (GridView) view.findViewById(R.id.gridview_popular);
        listURL = new ArrayList<>();
        listMovie = new ArrayList<>();
        imageAdapter = new ImageAdapter(getActivity().getApplicationContext(), listURL);
        page = 0;
        callApi();
        gridview.setAdapter(imageAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                String url = listURL.get(position);
                MovieDetails movie = listMovie.get(position);

                Intent detailIntent = new Intent(getActivity().getApplicationContext(), DetailActivity.class);
                detailIntent.putExtra("url",url);
                detailIntent.putExtra("title",movie.getTitle());
                detailIntent.putExtra("synopsis",movie.getSynopsis());
                detailIntent.putExtra("rating",movie.getRating());
                detailIntent.putExtra("release",movie.getRelease());
                detailIntent.putExtra("backdrop",movie.getBackdrop());
                detailIntent.putExtra("id",movie.getID());

                startActivity(detailIntent);
            }
        });

        gridview.setOnScrollListener(new AbsListView.OnScrollListener(){
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
            {
                if(firstVisibleItem + visibleItemCount >= totalItemCount && totalItemCount >= page * 20){
                    callApi();
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState){
            }
        });

        return view;
    }



    public void callApi(){
        page++;
        ApiServicePopular service = ApiHelper.client().create(ApiServicePopular.class);
        Call<PopularModel> call = service.getPopularMovie(Key.getAPIKey(), page);
        call.enqueue(new Callback<PopularModel>() {
            @Override
            public void onResponse(Call<PopularModel> call, Response<PopularModel> response) {
                if(response.body() != null) {
                    List<Result> popularList = response.body().getResults();
                    for (Result popular : popularList) {
                        String url = "https://image.tmdb.org/t/p/w500/" + popular.getPosterPath();
                        String title    = popular.getTitle();
                        String release  = popular.getReleaseDate();
                        Double rating   = popular.getVoteAverage();
                        String synopsis = popular.getOverview();
                        String backgrop = "https://image.tmdb.org/t/p/w500/" + popular.getBackdropPath();
                        Integer id = popular.getId();

                        MovieDetails movie = new MovieDetails();
                        movie.setTitle(title);
                        movie.setSynopsis(synopsis);
                        movie.setRating(rating);
                        movie.setRelease(release);
                        movie.setBackdrop(backgrop);
                        movie.setID(id);
                        listURL.add(url);
                        listMovie.add(movie);

                    }

                    imageAdapter.refreshData(listURL);
                }
            }
            @Override
            public void onFailure(Call<PopularModel> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Unable to Load Some Components",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
