package com.mrapp.ratingdataservice.resources;

/*
 Author: MohammadReza Ahmadi,  "m.reza79ahmadi@gmail.com"
 5/9/2020, 1:40 AM
*/

import com.mrapp.ratingdataservice.models.Rating;
import com.mrapp.ratingdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {
    private int callNumber = 0;

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 6);
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId) throws InterruptedException {
        System.out.println("callNumber= " + (++callNumber));
        List<Rating> ratingList = Arrays.asList(
                new Rating("101", 4),
                new Rating("102", 3)
        );

        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setRatingList(ratingList);
//        Thread.sleep(3000);
        return userRating;
    }
}
