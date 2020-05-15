package com.mrapp.movieinfoservice.models;

/*
 Author: MohammadReza Ahmadi,  "m.reza79ahmadi@gmail.com"
 5/9/2020, 2:02 AM
*/

public class Movie {
    private String movieId;
    private String name;

    public Movie(String movieId, String name) {
        this.movieId = movieId;
        this.name = name;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
