/*
 * You can use the following import statements
 * 
 * import org.springframework.web.server.ResponseStatusException;
 * import org.springframework.http.HttpStatus;
 * 
 */

package com.example.movie;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


import java.util.*;

// Do not modify the below code

public class MovieService implements MovieRepository {

    private static HashMap<Integer, Movie> movieList = new HashMap<>();

    public MovieService() {
        movieList.put(1, new Movie(1, "Avengers: Endgame", "Robert Downey Jr."));
        movieList.put(2, new Movie(2, "Avatar", "Sam Worthington"));
        movieList.put(3, new Movie(3, "Titanic", "Leonardo DiCaprio"));
        movieList.put(4, new Movie(4, "Star Wars: The Force Awakens", "Daisy Ridley"));
        movieList.put(5, new Movie(5, "Jurassic World", "Chris Pratt"));
    }

    // Do not modify the above code
    int uniqueId = 6;

    @Override
    public void deleteMovie(int movieId) {
        Movie delMovie = movieList.get(movieId);
        if (delMovie == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            movieList.remove(movieId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public Movie updateMovie(int movieId, Movie movie) {
        Movie existingMovie = movieList.get(movieId);
        if (existingMovie == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (existingMovie != null) {
            existingMovie.setMovieName(movie.getMovieName());
        }

        if (existingMovie != null) {
            existingMovie.setLeadActor(movie.getLeadActor());
        }

        return existingMovie;
    }

    @Override
    public Movie getMovieById(int movieId) {
        Movie uniqueMovie = movieList.get(movieId);
        if (uniqueMovie == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return uniqueMovie;
    }

    @Override
    public Movie addMovie(Movie movie){
        movie.setMovieId(uniqueId);
        movieList.put(uniqueId, movie);
        uniqueId+=1;

        return movie;
    }

    @Override
    public List<Movie> getMovies() {
        Collection<Movie> allMovieList = movieList.values();
        List<Movie> allMovieLists = new ArrayList<>(allMovieList);
        return allMovieLists;
    }

}
