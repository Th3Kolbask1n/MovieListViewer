package com.alexp.moviesreview;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "favourite_movies")
public class Movie implements Serializable {

    @PrimaryKey
    @SerializedName("kinopoiskId")

    private int kinopoiskId;
    @SerializedName("nameRu")

    private String nameRu;
    @SerializedName("nameOriginal")

    private String nameOriginal;
    @SerializedName("posterUrl")

    private String posterUrl;

    @SerializedName("posterUrlPreview")
    private String posterUrlPreview;
    @SerializedName("year")

    private int year;
    @SerializedName("ratingKinopoisk")

    private double ratingKinopoisk;

    public Movie(int kinopoiskId, String nameRu, String nameOriginal, String posterUrl, String posterUrlPreview, int year, double ratingKinopoisk) {
        this.kinopoiskId = kinopoiskId;
        this.nameRu = nameRu;
        this.nameOriginal = nameOriginal;
        this.posterUrl = posterUrl;
        this.posterUrlPreview = posterUrlPreview;
        this.year = year;
        this.ratingKinopoisk = ratingKinopoisk;
    }

    public int getKinopoiskId() {
        return kinopoiskId;
    }

    public String getNameRu() {
        return nameRu;
    }

    public String getNameOriginal() {
        return nameOriginal;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getPosterUrlPreview() {
        return posterUrlPreview;
    }

    public int getYear() {
        return year;
    }

    public double getRatingKinopoisk() {
        return ratingKinopoisk;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "kinopoiskId=" + kinopoiskId +
                ", nameRu='" + nameRu + '\'' +
                ", nameOriginal='" + nameOriginal + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", posterUrlPreview='" + posterUrlPreview + '\'' +
                ", year=" + year +
                ", ratingKinopoisk='" + ratingKinopoisk + '\'' +
                '}';
    }
}
