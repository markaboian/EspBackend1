package com.dh.catalogservice.controller;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.client.ISerieClient;
import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.queue.Listener;
import com.dh.catalogservice.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class CatalogController {

    @Autowired
    private IMovieClient iMovieClient;

    @Autowired
    private ISerieClient iSerieClient;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private Listener listener;

    private static Logger log = Logger.getLogger(CatalogController.class.getName());

    @GetMapping("/catalog/movie/{genre}")
    public ResponseEntity<List<Movie>> getCatalogByGenre (@PathVariable String genre) {
        log.info("Mostrando las peliculas del genero " + genre);
        return iMovieClient.getMovieByGenre(genre);
    }

    @PostMapping("/catalog/movie/save")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        log.info("Guardando la pelicula");
        listener.receive(movie);
        return iMovieClient.saveMovie(movie);
    }

    @GetMapping("/catalog/serie/{genre}")
    public ResponseEntity<List<Serie>> getSerieByGenre (@PathVariable String genre) {
        log.info("Mostrando las series del genero " + genre);
        return iSerieClient.getSerieByGenre(genre);
    }

    @PostMapping("/catalog/serie/save")
    public String saveSerie (@RequestBody Serie serie) {
        log.info("Guardando la serie");
        listener.receive(serie);
        return iSerieClient.createSerie(serie);
    }

    @GetMapping("/catalog/{genre}")
    public Genre getAll (@PathVariable String genre) {
        log.info("Mostrando las peliculas y series del genero " + genre);
        return catalogService.findByGenre(genre);
    }
}
