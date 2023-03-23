package com.kyriakos.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kyriakos.entity.MovieEntity;
import com.kyriakos.exception.RentMoviesApplicationException;
import com.kyriakos.model.Movies;
import com.kyriakos.repository.MovieRepository;
import com.kyriakos.util.ResultBuilder;
import com.kyriakos.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ResultBuilder resultBuilder;

    @Autowired
    RentMoviesApplicationException rentMoviesApplicationException;

    @Autowired
    Validator validator;

    /* Find a movie in the database by its id. */
    public ResponseEntity<String> getMovieById(int id) throws JsonProcessingException {

        try {
            String response = resultBuilder.buildResultForGetMovieByID(movieRepository.findById(id).get());
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (java.util.NoSuchElementException noSuchElementException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(rentMoviesApplicationException.getErrorForMovieNotExistForSpecificIDExceptionTextMessage(id));
        }

    }

    /* Return all the movies from the database. */
    public List<MovieEntity> getAllMovies() {
        List<MovieEntity> movieEntities = new ArrayList<MovieEntity>();
        movieRepository.findAll().forEach(movie -> movieEntities.add(movie));
        return movieEntities;
    }

    /* Return info regarding the movies that are going to be rent among the total price that has to be paid. */
    public ResponseEntity<String> rentMovies(Movies movies) throws JsonProcessingException {

        /*Check the provided input for missing parameters*/
        if (validator.checkMovieStructure(movies, false).getStatusCode() != HttpStatus.OK) {
            return validator.checkMovieStructure(movies, false);
        }

        /* Validate if the requested movies exist in the database. */
        ResponseEntity<String> validateMovies = checkMovies(movies);
        if (!(validateMovies.getStatusCode() == HttpStatus.OK)) {
            return validateMovies;
        }

        /* Get movies from the database and construct the response*/
        String response = resultBuilder.buildResultForRentMovies(getMovies(movies));

        /* Return the response if all the validations have passed successfully */
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    /* Save or Update a movie in the database. */
    public ResponseEntity<String> saveOrUpdate(MovieEntity movieEntity) throws JsonProcessingException {

        /*Check the provided input for missing parameters*/
        if (validator.checkMovieEntityStructure(movieEntity).getStatusCode() != HttpStatus.OK) {
            return validator.checkMovieEntityStructure(movieEntity);
        }

        try {

            movieRepository.save(movieEntity);
            String result = resultBuilder.buildResultForAddMovie(movieEntity);

            /* Return the response if all the validations have passed successfully */
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(result);

        } catch (org.springframework.dao.DataIntegrityViolationException dataIntegrityViolationException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("");
        }

    }

    public ResponseEntity<String> deleteMovieById(int id) throws JsonProcessingException {

        /* Validate if the requested movies exist in the database. */
        if (getMovieById(id).getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(rentMoviesApplicationException.getErrorForMovieNotExistForSpecificIDExceptionTextMessage(id));
        }

        movieRepository.deleteById(id);

        /* Return the response if all the validations have passed successfully */
        return ResponseEntity.status(HttpStatus.OK)
                .body("{\"info\":\"The movie with id : " + id + " has been deleted.\"}");

    }

    /* Check if the provided movies exist in the database. */
    public ResponseEntity<String> checkMovies(Movies movies) {

        ArrayList moviesThatNotExist = new ArrayList<>();
        Boolean foundMovieThatNotExist = false;

        Iterator<String> it = movies.getRent().iterator();
        while (it.hasNext()) {

            String movieName = it.next();
            MovieEntity movieEntity = getMovieByName(movieName);
            if (movieEntity == null) {
                moviesThatNotExist.add(movieName);
                foundMovieThatNotExist = true;
            }
        }

        if (foundMovieThatNotExist) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(rentMoviesApplicationException.getErrorForMovieNotExistExceptionTextMessage(moviesThatNotExist.toString()));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body("OK");
    }

    /* Get the provided movies exist from the database. */
    public ArrayList<MovieEntity> getMovies(Movies movies) {

        ArrayList<MovieEntity> moviesThatExist = new ArrayList<>();

        Iterator<String> it = movies.getRent().iterator();
        while (it.hasNext()) {

            String movieName = it.next();
            MovieEntity movieEntity = getMovieByName(movieName);
            moviesThatExist.add(movieEntity);

        }

        return moviesThatExist;
    }


    /* Find a movie in the database by its name. */
    public MovieEntity getMovieByName(String movieName) {

        MovieEntity movieEntity = movieRepository.findByName(movieName);
        return movieEntity;
    }


}