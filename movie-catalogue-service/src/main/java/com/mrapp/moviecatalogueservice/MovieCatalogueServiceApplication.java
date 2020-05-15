package com.mrapp.moviecatalogueservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.TimeUnit;

//import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class MovieCatalogueServiceApplication {

    private final int TIMEOUT = (int) TimeUnit.SECONDS.toMillis(100);

    public static void main(String[] args) {
        SpringApplication.run(MovieCatalogueServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setReadTimeout(100000);
        httpComponentsClientHttpRequestFactory.setConnectTimeout(100000);
        httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(100000);
        return new RestTemplate(httpComponentsClientHttpRequestFactory);
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }

}
