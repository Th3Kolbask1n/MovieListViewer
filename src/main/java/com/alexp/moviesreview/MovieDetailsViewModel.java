package com.alexp.moviesreview;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailsViewModel extends AndroidViewModel {
    private static final String TAG = "MovieDetailsViewModel";

    private MutableLiveData<List<Trailer>> trailers = new MutableLiveData<>();
    private MutableLiveData<DescriptionResponce> descriptions = new MutableLiveData<>();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MovieDao movieDao;

    private int page = 1;

    public MutableLiveData<DescriptionResponce> getDescriptions() {
        return descriptions;
    }

    public LiveData<List<Trailer>> getTrailers() {
        return trailers;
    }
    public MutableLiveData<ReviewResponse> reviews = new MutableLiveData<>();
    public LiveData<ReviewResponse> getReviews() {
        return reviews;
    }
    public LiveData<Movie> getFavouriteMovie(int movieId)
    {
        return movieDao.getFavouriteMovieById(movieId);
    }
    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        this.movieDao = MovieDatabase.getInstance(application).movieDao();
    }

    public void loadTrailers(int id) {
        Log.d(TAG, String.valueOf(id));
       Disposable disposable =  ApiFactory.apiService.loadTrailers(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
               .map(new Function<TrailerResponse, List<Trailer>>() {
                   @Override
                   public List<Trailer> apply(TrailerResponse trailerResponse) throws Throwable {
                       return trailerResponse.getItems();
                   }
               })
                .subscribe(new Consumer<List<Trailer>>() {
                    @Override
                    public void accept(List<Trailer> trailerList) throws Throwable {

                        Log.d(TAG, trailerList.toString());
                        trailers.setValue(trailerList);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });

       compositeDisposable.add(disposable);
    }
    public void loadDescriptions(int id)
    {

      Disposable disposable =   ApiFactory.apiService.loadDescriptions(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DescriptionResponce>() {
                    @Override
                    public void accept(DescriptionResponce descriptionResponce) throws Throwable {

                        Log.d(TAG,descriptionResponce.toString());
                        descriptions.setValue(descriptionResponce);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG,throwable.toString());

                    }
                });
    compositeDisposable.add(disposable);
    }

    public void loadReviews(int id)
    {
        Disposable disposable = ApiFactory.apiService.loadReviews(id,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReviewResponse>() {
                    @Override
                    public void accept(ReviewResponse reviewResponse) throws Throwable {
                        ReviewResponse movieResponses = reviews.getValue();
                            if(movieResponses == null)
                            {
                                reviews.setValue(reviewResponse);
                            }
                            else
                            {
                                movieResponses.items.addAll(reviewResponse.items);
                            }
                        page++;

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void insertMovie(Movie movie)
    {
        Disposable disposable = movieDao.insertMovie(movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());

                    }
                });
        compositeDisposable.add(disposable);
    }

    public void deleteMovie(Movie movie)
    {
     Disposable disposable = movieDao.removeMovie(movie.getKinopoiskId())
             .subscribeOn(Schedulers.io())
             .subscribe();
     compositeDisposable.add(disposable);
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
