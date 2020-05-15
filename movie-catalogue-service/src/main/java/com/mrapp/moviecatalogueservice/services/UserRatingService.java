package com.mrapp.moviecatalogueservice.services;

/*
 Author: MohammadReza Ahmadi,  "m.reza79ahmadi@gmail.com"
 5/15/2020, 2:30 PM
*/

import com.mrapp.moviecatalogueservice.models.Rating;
import com.mrapp.moviecatalogueservice.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingService {
    @Autowired
    RestTemplate restTemplate;
    private int callTime = 0;

    @HystrixCommand(fallbackMethod = "getFallbackUserRating",
            commandProperties = {
                    /* Circuit breaker pattern properties*/
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "3"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "20"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "8000"),

                    /* Bulkhead pattern properties*/
                    @HystrixProperty(name = "coreSize", value = "20"),
                    @HystrixProperty(name = "mazQueueSize", value = "10")

            }
    )
    public UserRating getUserRating(@PathVariable("userId") String userId) {
        System.out.println("calling-" + (++callTime));
        return restTemplate.getForObject("http://rating-data-service/ratingsdata/users/" + userId, UserRating.class);
    }

    private UserRating getFallbackUserRating(@PathVariable("userId") String userId) {
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setRatingList(Arrays.asList(new Rating("!! no movie id", 0)));
        return userRating;
    }
}
