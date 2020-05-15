package com.mrapp.moviecatalogueservice.models;

/*
 Author: MohammadReza Ahmadi,  "m.reza79ahmadi@gmail.com"
 5/9/2020, 2:02 AM
*/

import javax.persistence.*;

@Entity
@Table(name = "MOVIE")
public class Movie {
    private Integer id;
    private String movieId;
    private String name;

    public Movie() {
    }

    public Movie(String movieId, String name) {
        this.movieId = movieId;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
