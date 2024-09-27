package com.alexp.moviesreview;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DescriptionResponce {

    @SerializedName("description")
    private String description;
    @SerializedName("shortDescription")

    private String shortDescription;

    public DescriptionResponce(String description, String shortDescription) {
        this.description = description;
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    @Override
    public String toString() {
        return "DescriptionResponce{" +
                "description='" + description + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                '}';
    }
}
