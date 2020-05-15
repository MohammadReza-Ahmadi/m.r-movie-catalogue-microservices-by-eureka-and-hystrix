package com.mrapp.moviecatalogueservice.resources;

/*
 Author: MohammadReza Ahmadi,  "m.reza79ahmadi@gmail.com"
 5/8/2020, 7:48 PM
*/


import com.mrapp.moviecatalogueservice.models.CatalogueItem;
import com.mrapp.moviecatalogueservice.models.Movie;
import com.mrapp.moviecatalogueservice.models.Rating;
import com.mrapp.moviecatalogueservice.models.UserRating;
import com.mrapp.moviecatalogueservice.services.MovieInfoService;
import com.mrapp.moviecatalogueservice.services.UserRatingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalogueV3")
public class MovieCatalogueResourceV3 {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private MovieInfoService movieInfoService;
    @Autowired
    private UserRatingService userRatingService;


    @RequestMapping("/{userId}")
    public List<CatalogueItem> getCatalogues(@PathVariable("userId") String userId) {
        UserRating userRating = userRatingService.getUserRating(userId);
        List<CatalogueItem> catalogueItemList = userRating.getRatingList().stream()
                .map(rating -> movieInfoService.getCatalogueItem(rating)).collect(Collectors.toList());

        return catalogueItemList;
    }



}
