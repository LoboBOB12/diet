package com.noexerciseweightlosst.wqxw.model;

public class RecipeModel {

    int image;
    float rate;
    String title,description;

    public RecipeModel(int image, String title, float rate, String description) {
        this.image = image;
        this.title = title;
        this.rate = rate;
        this.description = description;

    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public float getRate() {
        return rate;
    }
}

