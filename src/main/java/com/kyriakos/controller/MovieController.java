package com.kyriakos.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kyriakos.entity.MovieEntity;
import com.kyriakos.model.Movies;
import com.kyriakos.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class MovieController {

    @Autowired
    MovieService movieService;

    /*Return all the available movies in the database*/
    @GetMapping("/movies")
    public List<MovieEntity> getAllMovies() {
        return movieService.getAllMovies();
    }

}