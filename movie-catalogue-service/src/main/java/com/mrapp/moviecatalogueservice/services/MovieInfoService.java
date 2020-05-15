package com.mrapp.moviecatalogueservice.services;

/*
 Author: MohammadReza Ahmadi,  "m.reza79ahmadi@gmail.com"
 5/15/2020, 2:21 PM
*/

import com.mrapp.moviecatalogueservice.models.CatalogueItem;
import com.mrapp.moviecatalogueservice.models.Movie;
import com.mrapp.moviecatalogueservice.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovieInfoService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder webClientBuilder;


    @HystrixCommand(fallbackMethod = "getFallbackCatalogueItem")
    public CatalogueItem getCatalogueItem(Rating rating) {
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

    private CatalogueItem getFallbackCatalogueItem(Rating rating) {
        return new CatalogueItem("!! No Movie name", "!! nothing", rating.getRating());
    }
}
