/*
package com.mrapp.moviecatalogueservice.services;

*/
/*
 Author: MohammadReza Ahmadi,  "m.reza79ahmadi@gmail.com"
 5/15/2020, 11:31 AM
*//*


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class LoadBalancers {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("customer-service", route -> route.path("/customers")
                        .uri("lb://customer-service"))
                .build();
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

}
*/
