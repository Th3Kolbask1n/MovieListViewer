package com.alexp.moviesreview;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>{

    private List<Movie> movieList = new ArrayList<>();
private OnReachEndListener onReachEndListener;
private OnMovieClickListener onMovieClickListener;

    public void setOnMovieClickListener(OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
    }

    public void setOnReachEndListener(OnReachEndListener onReachEndListener) {
        this.onReachEndListener = onReachEndListener;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,
                parent,
                false
                        );
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        Glide.with(holder.itemView)
                .load(movie.getPosterUrlPreview())
                .into(holder.imageViewPoster);
        double movieRating  = movie.getRatingKinopoisk();
        int backgroundId;
        if(movieRating>7)
            backgroundId = R.drawable.circle_green;
        else if (movieRating>5) backgroundId = R.drawable.circle_orange;
        else backgroundId = R.drawable.circle_red;
        Drawable background = ContextCompat.getDrawable(holder.itemView.getContext(),backgroundId);
        holder.textViewRating.setBackground(background);
        holder.textViewRating.setText(String.valueOf(movie.getRatingKinopoisk()));

        if(position >= movieList.size()-6 && onReachEndListener!=null)
        {
            onReachEndListener.onReachEnd();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onMovieClickListener!=null) {
                    onMovieClickListener.OnMovieClick(movie);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
    interface OnReachEndListener{
        void onReachEnd();
    }
    interface OnMovieClickListener{
        void OnMovieClick(Movie movie);
    }
    static class MovieViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imageViewPoster;
        private final TextView textViewRating;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            textViewRating = itemView.findViewById(R.id.textViewRating);
        }
    }

}
