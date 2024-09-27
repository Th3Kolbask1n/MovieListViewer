package com.alexp.moviesreview;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("api/v2.2/films?order=NUM_VOTE&type=ALL&ratingFrom=4&ratingTo=10&yearFrom=2000&yearTo=3000")
    @Headers("X-API-KEY:")
    Single<MovieResponse> loadMoviesList(@Query("page")int page);

    @GET("api/v2.2/films/{id}/videos")
    @Headers("X-API-KEY:")
    Single<TrailerResponse> loadTrailers(@Path("id") int id);


    @GET("api/v2.2/films/{id}")
    @Headers("X-API-KEY:")
    Single<DescriptionResponce> loadDescriptions(@Path("id") int id);

    @GET("api/v2.2/films/{id}/reviews?page=1&order=DATE_DESC")
    @Headers("X-API-KEY:")
    Single<ReviewResponse> loadReviews(@Path("id") int id, @Query("page")int page);
}



