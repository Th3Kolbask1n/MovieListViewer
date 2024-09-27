package com.alexp.moviesreview;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerResponse {
    @Override
    public String toString() {
        return "TrailerResponse{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }

    public TrailerResponse(int total, List<Trailer> items) {
        this.total = total;
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public List<Trailer> getItems() {
        return items;
    }

    @SerializedName("total")
    private int total;
    @SerializedName("items")
    private List<Trailer> items;

}
