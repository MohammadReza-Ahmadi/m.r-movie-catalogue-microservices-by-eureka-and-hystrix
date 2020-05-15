package com.mrapp.moviecatalogueservice.resources;

/*
 Author: MohammadReza Ahmadi,  "m.reza79ahmadi@gmail.com"
 5/8/2020, 7:48 PM
*/


import com.mrapp.moviecatalogueservice.models.CatalogueItem;
import com.mrapp.moviecatalogueservice.models.Movie;
import com.mrapp.moviecatalogueservice.models.Rating;
import com.mrapp.moviecatalogueservice.models.UserRating;
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
@RequestMapping("/catalogueV2")
public class MovieCatalogueResourceV2 {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder webClientBuilder;


    @RequestMapping("/{userId}")
    @HystrixCommand(fallbackMethod = "getFallbackCatalogue")
    public List<CatalogueItem> getCatalogues(@PathVariable("userId") String userId) {
        UserRating userRating = getUserRating(userId);
        List<CatalogueItem> catalogueItemList = userRating.getRatingList().stream()
                .map(rating -> getCatalogueItem(rating)).collect(Collectors.toList());

        return catalogueItemList;
    }

    public List<CatalogueItem> getFallbackCatalogue(@PathVariable("userId") String userId) {
        System.out.println("getFallbackCatalogue calling !!");
        return Arrays.asList(new CatalogueItem("!! No Movie name", "!! nothing", 0));
    }

    private UserRating getUserRating(@PathVariable("userId") String userId) {
        //UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);
        return restTemplate.getForObject("http://rating-data-service/ratingsdata/users/" + userId, UserRating.class);
    }

    private CatalogueItem getCatalogueItem(Rating rating) {
        /*model-1: restTemplate & direct call without using eureka server*/
        //Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);

        /*model-2: restTemplate & by using eureka server for discovering registered service name*/
//        return restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

        /*model-3: webClientBuilder & by using eureka server for discovering registered service name*/
        Movie movie = webClientBuilder.build()
                .get()
//                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                .uri("http://movie-info-service/movies/" + rating.getMovieId())
                .retrieve()
                .bodyToMono(Movie.class)
                .block();


        return new CatalogueItem(movie.getName(), "Desc", rating.getRating());
    }

}
