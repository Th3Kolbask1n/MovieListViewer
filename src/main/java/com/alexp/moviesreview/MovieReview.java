package com.alexp.moviesreview;

import com.google.gson.annotations.SerializedName;

public class MovieReview {

    @SerializedName("kinopoiskId")
    int kinopoiskId;
    @SerializedName("type")
    String type;
    @SerializedName("date")
    String date;
    @SerializedName("positiveRating")
    int positiveRating;
    @SerializedName("negativeRating")
    int negativeRating;
    @SerializedName("author")
    String author;
    @SerializedName("title")
    String title;
    @SerializedName("description")
    String description;


    public MovieReview(int kinopoiskId, String type, String date, int positiveRating, int negativeRating, String author, String title, String description) {
        this.kinopoiskId = kinopoiskId;
        this.type = type;
        this.date = date;
        this.positiveRating = positiveRating;
        this.negativeRating = negativeRating;
        this.author = author;
        this.title = title;
        this.description = description;
    }

    public int getKinopoiskId() {
        return kinopoiskId;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public int getPositiveRating() {
        return positiveRating;
    }

    public int getNegativeRating() {
        return negativeRating;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "MovieReview{" +
                "kinopoiskId=" + kinopoiskId +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                ", positiveRating=" + positiveRating +
                ", negativeRating=" + negativeRating +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

