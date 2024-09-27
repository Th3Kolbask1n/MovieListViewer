package com.alexp.moviesreview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailsActivity extends AppCompatActivity {
    private static final String TAG = "MovieDetailsActivity";
    private static final String EXTRA_MOVIE = "movie";
    private ImageView imageViewPoster;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewDescription;

    private MovieDetailsViewModel movieDetailsViewModel;
    private RecyclerView recyclerViewTrailers;
    private TrailersAdapter trailersAdapter;
    private MovieReviewAdapter movieReviewAdapter;
    private RecyclerView recyclerViewReviews;
    private ImageView starImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movieDetailsViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);
        initView();
        trailersAdapter = new TrailersAdapter();
        movieReviewAdapter = new MovieReviewAdapter();
        recyclerViewTrailers.setAdapter(trailersAdapter);
        recyclerViewReviews.setAdapter(movieReviewAdapter);
        Movie movie =(Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);

        Glide.with(this)
                .load(movie.getPosterUrl())
                .into(imageViewPoster);
        textViewTitle.setText(movie.getNameRu());
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewDescription.setText(movie.getNameOriginal());

        movieDetailsViewModel.loadTrailers(movie.getKinopoiskId());
        movieDetailsViewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                trailersAdapter.setTrailerList(trailers);
            }
        });

        movieDetailsViewModel.loadDescriptions(movie.getKinopoiskId());
        movieDetailsViewModel.getDescriptions().observe(this, new Observer<DescriptionResponce>() {
            @Override
            public void onChanged(DescriptionResponce descriptionResponce) {
                textViewDescription.setText(descriptionResponce.getDescription());
            }
        });

        movieDetailsViewModel.loadReviews(movie.getKinopoiskId());
        movieDetailsViewModel.getReviews().observe(this, new Observer<ReviewResponse>() {
            @Override
            public void onChanged(ReviewResponse reviewResponse) {
                movieReviewAdapter.setMovieReviewList(reviewResponse.items);
            }
        });
        movieReviewAdapter.setOnReachEndListener(new MovieReviewAdapter.OnReachEndListener() {
            @Override
            public void onReachedEnd() {
                movieDetailsViewModel.loadReviews(movie.getKinopoiskId());
            }
        });

        trailersAdapter.setOnTrailerClickListener(new TrailersAdapter.OnTrailerClickListener() {
            @Override
            public void onTrailerClick(Trailer trailer) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(trailer.getUrl()));
                startActivity(intent);
            }
        });
        Drawable starOff = ContextCompat.getDrawable(MovieDetailsActivity.this,
                android.R.drawable.star_off);
        Drawable starOn = ContextCompat.getDrawable(MovieDetailsActivity.this,
                android.R.drawable.star_on);


        movieDetailsViewModel.getFavouriteMovie(movie.getKinopoiskId()).observe(
                this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movieFromDb) {

                if(movieFromDb==null)
                {
                    starImageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            movieDetailsViewModel.insertMovie(movie);
                        }
                    });
                    starImageView.setImageDrawable(starOff);

                }
                else
                {
                    starImageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            movieDetailsViewModel.deleteMovie(movie);
                        }
                    });
                    starImageView.setImageDrawable(starOn);
                }

            }
        });
    }

    private void initView(){
        imageViewPoster = findViewById(R.id.ImageViewPoster);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDescription = findViewById(R.id.textViewDescription);
        recyclerViewTrailers = findViewById(R.id.recyclerViewTrailers);
        recyclerViewReviews = findViewById(R.id.recyclerViewReview);
        starImageView = findViewById(R.id.imageViewStar);
    }

    public static Intent newIntent(Context context, Movie movie)
    {
        Intent intent = new Intent(context,MovieDetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE,movie);
        return intent;

    }
}