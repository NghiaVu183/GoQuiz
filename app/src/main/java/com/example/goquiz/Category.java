package com.example.goquiz;

public class Category {
    private String name;
    private int image;
    private String description;

    public Category(String name, int image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}
