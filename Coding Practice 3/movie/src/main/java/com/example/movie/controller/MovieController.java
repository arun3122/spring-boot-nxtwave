package com.example.movie.controller;

import com.example.movie.model.Movie;
import com.example.movie.service.MovieH2Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class MovieController {

    @Autowired
    public MovieH2Service movieObj;

    @DeleteMapping("/movies/{movieId}")
    public void deleteMovie(@PathVariable("movieId") int movieId) {
        movieObj.deleteMovie(movieId);
    }

    @PutMapping("/movies/{movieId}")
    public Movie updateMovie(@PathVariable("movieId") int movieId, @RequestBody Movie movie) {
        return movieObj.updateMovie(movieId, movie);
    }

    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieObj.addMovie(movie);
    }

    @GetMapping("/movies")
    public ArrayList<Movie> getMovies() {
        return movieObj.getMovies();
    }

    @GetMapping("/movies/{movieId}")
    public Movie getMovieById(@PathVariable("movieId") int movieId) {
        return movieObj.getMovieById(movieId);
    }
}