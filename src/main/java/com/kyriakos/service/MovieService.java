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



    /* Return all the movies from the database. */
    public List<MovieEntity> getAllMovies() {
        List<MovieEntity> movieEntities = new ArrayList<MovieEntity>();
        movieRepository.findAll().forEach(movie -> movieEntities.add(movie));
        return movieEntities;
    }

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


}