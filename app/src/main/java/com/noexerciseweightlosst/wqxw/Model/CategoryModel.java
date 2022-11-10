package com.noexerciseweightlosst.wqxw.model;

public class CategoryModel {

    private String categoryTitle;
    private String categoryImage;
    private int categoryDrawableResourceId;

    public CategoryModel(String categoryTitle, int categoryDrawableResourceId) {
        this.categoryTitle = categoryTitle;
        this.categoryDrawableResourceId = categoryDrawableResourceId;
    }

    public CategoryModel(String categoryTitle, String categoryImage) {
        this.categoryTitle = categoryTitle;
        this.categoryImage = categoryImage;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public int getCategoryDrawableResourceId() {
        return categoryDrawableResourceId;
    }
}
