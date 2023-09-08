package com.dh.catalogservice.controller;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.client.ISerieClient;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatalogController {

    @Autowired
    private IMovieClient iMovieClient;

    @Autowired
    private ISerieClient iSerieClient;

    @GetMapping("/catalog/{genre}")
    public ResponseEntity<List<Movie>> getCatalogByGenre (@PathVariable String genre) {
        return iMovieClient.getMovieByGenre(genre);
    }

    @PostMapping("/catalog/save")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return iMovieClient.saveMovie(movie);
    }

    @GetMapping()
    public ResponseEntity<List<Serie>> getSerieByGenre (@PathVariable String genre) {
        return iSerieClient.getSerieByGenre(genre);
    }

    @PostMapping()
    public ResponseEntity<Serie> saveSerie (@RequestBody Serie serie) {
        return iSerieClient.saveSerie(serie);
    }
}
