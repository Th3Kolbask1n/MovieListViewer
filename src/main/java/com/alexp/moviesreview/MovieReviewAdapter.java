package com.alexp.moviesreview;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewAdapter.MovieViewHolder> {


    public void setOnReachEndListener(OnReachEndListener onReachEndListener) {
        this.onReachEndListener = onReachEndListener;
    }

    public OnReachEndListener onReachEndListener;
    public void setMovieReviewList(List<MovieReview> movieReviewList) {
        this.movieReviewList = movieReviewList;
        notifyDataSetChanged();

    }

    List<MovieReview> movieReviewList = new ArrayList<>();
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item,
                parent,false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieReview movieReview = movieReviewList.get(position);
        holder.userName.setText(movieReview.author);
        holder.reviewText.setText(movieReview.description);
        String typeReview = movieReview.type;
        int backgroundId =android.R.color.holo_orange_light;

        if(typeReview.contains("POSITIVE")) {
            backgroundId = android.R.color.holo_green_light;

        }
        else  if(typeReview.contains("NEGATIVE"))
            backgroundId = android.R.color.holo_red_light;

        int backGroup = ContextCompat.getColor(holder.itemView.getContext(),backgroundId);
        holder.linearLayout.setBackgroundColor(backGroup);
        if(position>=movieReviewList.size()-5  && onReachEndListener!=null)
        {
            onReachEndListener.onReachedEnd();
        }

    }

    @Override
    public int getItemCount() {
        return movieReviewList.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder
    {
        TextView userName;
        TextView reviewText;
        LinearLayout linearLayout;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.textViewUserName);
            reviewText = itemView.findViewById(R.id.textViewReview);
            linearLayout = itemView.findViewById(R.id.linerLayout);

        }
    }

    interface OnReachEndListener{
        void onReachedEnd();
    }
}
