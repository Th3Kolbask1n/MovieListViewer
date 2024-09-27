package com.alexp.moviesreview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class FavouriteMoviesActivity extends AppCompatActivity {

    private static final String TAG = "FavouriteMovies";

    private FavouriteMovieViewModel viewModel;
    private RecyclerView recyclerViewMovieList;
    private MovieListAdapter movieListAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_movies);
        initViews();
        movieListAdapter = new MovieListAdapter();
        recyclerViewMovieList.setAdapter(movieListAdapter);
        recyclerViewMovieList.setLayoutManager(new GridLayoutManager(this,2));
        viewModel = new ViewModelProvider(this).get(FavouriteMovieViewModel.class);

        viewModel.getMovieList().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieListAdapter.setMovieList(movies);

            }
        });

        movieListAdapter.setOnMovieClickListener(new MovieListAdapter.OnMovieClickListener() {
            @Override
            public void OnMovieClick(Movie movie) {
                Intent intent = MovieDetailsActivity.newIntent(FavouriteMoviesActivity.this, movie);
                startActivity(intent);
            }
        });
    }

    private void initViews()
    {
        recyclerViewMovieList = findViewById(R.id.recyclerViewFavoutiteMovies);


    }

    public static Intent newIntent(Context context)
    {
        return new Intent(context,FavouriteMoviesActivity.class);
    }
}