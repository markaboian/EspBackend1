package com.dh.catalogservice.service;

import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repository.IMovieRepository;
import com.dh.catalogservice.repository.ISerieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Genre findByGenre(String genre) {
        List<Serie> series = rSerie.findAllByGenre(genre);
        List<Movie> movies = rMovie.findAllByGenre(genre);
        return new Genre(movies, series);
    }
}
