package com.alexp.moviesreview;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {

    @SerializedName("total")
    int total;

    @SerializedName("items")
    List<MovieReview> items;

    public ReviewResponse(int total, List<MovieReview> items) {
        this.total = total;
        this.items = items;
    }

    @Override
    public String toString() {
        return "ReviewResponse{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }
}
