package com.dh.catalogservice.client;

import com.dh.catalogservice.client.feign.FeignConfiguration;
import com.dh.catalogservice.model.Movie;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name = "movie-service")
@LoadBalancerClient(name = "movie-service", configuration = FeignConfiguration.class)
public interface IMovieClient {

    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre);

}
