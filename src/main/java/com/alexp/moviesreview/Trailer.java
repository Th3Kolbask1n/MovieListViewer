package com.alexp.moviesreview;

import com.google.gson.annotations.SerializedName;

public class Trailer {

    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;

    @Override
    public String toString() {
        return "Trailer{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public Trailer(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
