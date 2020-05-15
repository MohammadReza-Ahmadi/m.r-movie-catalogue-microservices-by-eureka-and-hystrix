package com.mrapp.movieinfoservice.resources;

/*
 Author: MohammadReza Ahmadi,  "m.reza79ahmadi@gmail.com"
 5/9/2020, 1:59 AM
*/

import com.mrapp.movieinfoservice.models.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) throws InterruptedException {
//        Thread.sleep(2000);
        return new Movie(movieId, "First Movie");
    }
}
