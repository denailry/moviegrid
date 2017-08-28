package com.androidkejar.moviegrid;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

public class FragmentFavourite extends Fragment {

    ArrayList<String> listURL;
    ImageAdapter imageAdapter;
    ArrayList<MovieDetails> listMovie;
    Integer page;
    GridView gridview;

    public FragmentFavourite() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_favourite, container, false);

        gridview = (GridView) view.findViewById(R.id.gridview_favourite);
        listURL = new ArrayList<>();
        listMovie = new ArrayList<>();
        imageAdapter = new ImageAdapter(getActivity().getApplicationContext(), listURL);
        page = 0;
        loadDatabase();
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

        /*
        gridview.setOnScrollListener(new AbsListView.OnScrollListener(){
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
            {
                if(firstVisibleItem + visibleItemCount >= totalItemCount){
                    loadDatabase();
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState){
            }
        });
        */

        return view;
    }

    private void loadDatabase() {
        List<MovieFavourite> movieSearch = new Select()
                .from(MovieFavourite.class)
                .queryList();
        Integer x = movieSearch.size();
        for (MovieFavourite movieFav : movieSearch) {
            listURL.add(movieFav.getUrl());

            MovieDetails movie = new MovieDetails();
            movie.setTitle(movieFav.getTitle());
            movie.setSynopsis(movieFav.getSynopsis());
            movie.setRating(movieFav.getRating());
            movie.setRelease(movieFav.getRelease());
            movie.setBackdrop(movieFav.getBackdrop());
            movie.setID(movieFav.getID());
            listMovie.add(movie);
        }

        imageAdapter.refreshData(listURL);
    }

}
