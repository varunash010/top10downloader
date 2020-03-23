package com.example.top10downloader;

import androidx.annotation.NonNull;

public class FeedEntry {
    private String title;
    private String name;
    private String artist;
    private String category;
    private String price;
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Title: " + this.title + "\n" +
                "Artist: " + this.artist + "\n" +
                "Song: " + this.name + "\n" +
                "Price: " + this.price;
    }
}
