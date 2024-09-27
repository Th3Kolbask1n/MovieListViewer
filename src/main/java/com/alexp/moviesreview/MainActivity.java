package com.alexp.moviesreview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private MainViewModel viewModel;
    private RecyclerView recyclerViewMovieList;
    private MovieListAdapter movieListAdapter;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setTitle("Каталог фильмов");
        movieListAdapter = new MovieListAdapter();
        recyclerViewMovieList.setAdapter(movieListAdapter);
        recyclerViewMovieList.setLayoutManager(new GridLayoutManager(this,2));
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                Log.d("TAG",String.valueOf(movies.size()));

                movieListAdapter.setMovieList(movies);
            }
        });
        movieListAdapter.setOnReachEndListener(new MovieListAdapter.OnReachEndListener() {
            @Override
            public void onReachEnd() {
                viewModel.loadMovies();
            }
        });
        viewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if(isLoading)
                    progressBar.setVisibility(View.VISIBLE);
                else
                    progressBar.setVisibility(View.INVISIBLE);
            }
        });
        movieListAdapter.setOnMovieClickListener(new MovieListAdapter.OnMovieClickListener() {
            @Override
            public void OnMovieClick(Movie movie) {
                Intent intent = MovieDetailsActivity.newIntent(MainActivity.this,movie);
                startActivity(intent);
            }
        });


    }
    private void initView()
    {
        recyclerViewMovieList = findViewById(R.id.recyclerViewMovies);
        progressBar = findViewById(R.id.progressBarLoading);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.itemFavouriteMovies)
        {
            Intent intent = FavouriteMoviesActivity.newIntent(this);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}