package com.example.movie.service;

import com.example.movie.model.Movie;
import com.example.movie.model.MovieRowMapper;
import com.example.movie.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class MovieH2Service implements MovieRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public void deleteMovie(int movieId) {
        db.update("DELETE FROM movielist WHERE movieId = ?", movieId);
    }

    @Override
    public Movie updateMovie(int movieId, Movie movie) {
        if (movie.getMovieName() != null) {
            db.update("UPDATE movielist SET movieName = ? WHERE movieId = ?", movie.getMovieName(), movieId);
        }

        if (movie.getLeadActor() != null) {
            db.update("UPDATE movielist SET leadActor = ? WHERE movieId = ?", movie.getLeadActor(), movieId);
        }

        return getMovieById(movieId);
    }

    @Override
    public Movie addMovie(Movie movie) {
        db.update("INSERT INTO movielist(movieName, leadActor) VALUES(?, ?)", movie.getMovieName(), movie.getLeadActor());
        Movie newMovie = db.queryForObject("SELECT * FROM movielist WHERE movieName = ? AND leadActor = ?", new MovieRowMapper(), movie.getMovieName(), movie.getLeadActor());
        return newMovie;
    }

    @Override
    public ArrayList<Movie> getMovies() {
        List<Movie> movieList = db.query("SELECT * FROM movielist", new MovieRowMapper());
        ArrayList<Movie> movies = new ArrayList<>(movieList);
        return movies;
    }

    @Override
    public Movie getMovieById(int movieId) {
        try {
            Movie uniqueMovie = db.queryForObject("SELECT * FROM movielist WHERE movieId = ?", new MovieRowMapper(), movieId);
            return uniqueMovie;
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}