package com.dh.catalogservice.service;

import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repository.IMovieRepository;
import com.dh.catalogservice.repository.ISerieRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CatalogService {

    @Autowired
    private ISerieRepository rSerie;

    @Autowired
    private IMovieRepository rMovie;

    public void saveSerie(Serie serie) {
        rSerie.save(serie);
    }

    public void saveMovie(Movie movie) {
        rMovie.save(movie);
    }

    @CircuitBreaker(name = "catalog", fallbackMethod = "findAllByGenreEmpty")
    @Retry(name = "catalog")
    public Genre getAllByGenre(String genre, Boolean throwError) {
        List<Serie> listSerie = rSerie.findAllByGenre(genre);
        List<Movie> listMovie = rMovie.findAllByGenre(genre);
        if (throwError) {
            throw new RuntimeException();
        }
        return new Genre(listMovie, listSerie);
    }
    private Genre findAllByGenreEmpty(CallNotPermittedException exception){
        List<Serie> listSerie = new ArrayList<>();
        List<Movie> listMovie = new ArrayList<>();
        return new Genre(listMovie, listSerie);
    }
}
