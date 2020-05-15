package com.mrapp.moviecatalogueservice.models;

/*
 Author: MohammadReza Ahmadi,  "m.reza79ahmadi@gmail.com"
 5/9/2020, 1:45 AM
*/

public class Rating {
    private String movieId;
    private int rating;

    public Rating() {
    }

    public Rating(String movieId, int rating) {
        this.movieId = movieId;
        this.rating = rating;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
