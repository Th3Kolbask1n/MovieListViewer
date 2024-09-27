package com.alexp.moviesreview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class FavouriteMovieViewModel extends AndroidViewModel {
    private static final String TAG = "FavouriteMovieViewModel";
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private MovieDao movieDao;


    private final MutableLiveData<List<Movie>> movieList = new MutableLiveData<>();

    public FavouriteMovieViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getInstance(application).movieDao();
    }

    public LiveData<List<Movie>> getMovieList() {
        return movieDao.getAllFavouriteMovies();
    }

//    public void loadFavouriteMovies()
//    {
//        Disposable disposable = movieDao.getAllFavouriteMovies()
//                .observe(FavouriteMovies.class, new Observer<List<Movie>>() {
//                    @Override
//                    public void onChanged(List<Movie> movies) {
//
//                    }
//                });
//    }



}
