/*
 * You can use the following import statements
 * 
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 * 
 */


package com.example.movie;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
class MovieController {
    MovieService movieObj = new MovieService();

    @DeleteMapping("/movies/{movieId}")
    public void deleteMovie(@PathVariable("movieId") int movieId) {
        movieObj.deleteMovie(movieId);
    }

    @PutMapping("/movies/{movieId}")
    public Movie updateMovie(@PathVariable("movieId") int movieId, @RequestBody Movie movie) {
        return movieObj.updateMovie(movieId, movie);
    }

    @GetMapping("/movies/{movieId}")
    public Movie getMovieById(@PathVariable("movieId") int movieId) {
        return movieObj.getMovieById(movieId);
    }

    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieObj.addMovie(movie);
    }

    @GetMapping("/movies")
    public List<Movie> getMovies() {
        return movieObj.getMovies();
    }
}