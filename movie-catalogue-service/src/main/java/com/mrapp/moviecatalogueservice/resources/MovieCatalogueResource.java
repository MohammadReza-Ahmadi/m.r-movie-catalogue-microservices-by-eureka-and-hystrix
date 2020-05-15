package com.mrapp.moviecatalogueservice.resources;

/*
 Author: MohammadReza Ahmadi,  "m.reza79ahmadi@gmail.com"
 5/8/2020, 7:48 PM
*/


import com.mrapp.moviecatalogueservice.models.CatalogueItem;
import com.mrapp.moviecatalogueservice.models.Movie;
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
@RequestMapping("/catalogue")
public class MovieCatalogueResource {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder webClientBuilder;


/*    @RequestMapping("/{userId}")
    @HystrixCommand(fallbackMethod = "getFallbackCatalogue")
    public List<CatalogueItem> getCatalogues(@PathVariable("userId") String userId) {
//        UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);
        UserRating userRating = restTemplate.getForObject("http://rating-data-service/ratingsdata/users/" + userId, UserRating.class);
        System.out.println("give response...");
        List<CatalogueItem> catalogueItemList = userRating.getRatingList().stream().map(rating -> {
//            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
            webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();

            System.out.println("Under Block..");
            return new CatalogueItem(movie.getName(), "Desc", rating.getRating());

        }).collect(Collectors.toList());


        System.out.println("End of method..");
        return catalogueItemList;
    }*/


    @RequestMapping("/{userId}")
    @HystrixCommand(fallbackMethod = "getFallbackCatalogue")
    public List<CatalogueItem> getCatalogues(@PathVariable("userId") String userId) {
//        UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);
        UserRating userRating = restTemplate.getForObject("http://rating-data-service/ratingsdata/users/" + userId, UserRating.class);

        List<CatalogueItem> catalogueItemList = userRating.getRatingList().stream().map(rating -> {
            Movie movie = webClientBuilder.build()
                    .get()
//                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                    .uri("http://movie-info-service/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();

            return new CatalogueItem(movie.getName(), "Desc", rating.getRating());

        }).collect(Collectors.toList());

        return catalogueItemList;
    }


    public List<CatalogueItem> getFallbackCatalogue(@PathVariable("userId") String userId) {
        System.out.println("getFallbackCatalogue calling !!");
        return Arrays.asList(new CatalogueItem("!! No Movie name", "!! nothing", 0));
    }
}
