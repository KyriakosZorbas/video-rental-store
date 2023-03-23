package com.kyriakos.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kyriakos.Application;
import com.kyriakos.entity.MovieEntity;
import com.kyriakos.model.Movies;
import com.kyriakos.repository.MovieRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = Application.class)
public class MovieServiceTest {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    /* Test if getMovieByName method returns the correct movie */
    @Test
    public void testGetMovieByName() {
        MovieEntity movieEntity = getMovieEntity();
        movieRepository.save(movieEntity);

        String actualMovieName = "Matrix";
        String expectedMovieName = movieService.getMovieByName(movieEntity.getName()).getName();

        Assert.assertEquals(actualMovieName, expectedMovieName);
    }

    /* Test if getMovieById method returns the correct movie */
    @Test
    public void testGetMovieById() throws JsonProcessingException {
        MovieEntity movieEntity = getMovieEntity();
        movieRepository.save(movieEntity);

        Assert.assertEquals(movieService.getMovieById(movieEntity.getId()).getStatusCode(), HttpStatus.OK);

        Integer wrongId = 234;
        Assert.assertEquals(movieService.getMovieById(wrongId).getStatusCode(), HttpStatus.NOT_FOUND);
    }

    /* Test rentMovies method if returns correct statuses regarding the input*/
    @Test
    public void testRentMovies() throws JsonProcessingException {
        MovieEntity movieEntity = getMovieEntity();
        movieRepository.save(movieEntity);

        /* Give correct input */
        Assert.assertEquals((movieService.rentMovies(getMovies())).getStatusCode(), HttpStatus.OK);

        /*Give wrong input with missing parameters*/
        Assert.assertEquals((movieService.rentMovies(getWrongMovies())).getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);

        /*Give correct parameters but with wrong values*/
        Assert.assertEquals((movieService.rentMovies(getWrongValueMovies())).getStatusCode(), HttpStatus.BAD_REQUEST);

    }

    /* Test returnMovies method if returns correct statuses regarding the input*/
    @Test
    public void testReturnMovies() throws JsonProcessingException, ParseException {
        MovieEntity movieEntity = getMovieEntity();
        movieRepository.save(movieEntity);

        /* Give correct input */
        Assert.assertEquals((movieService.returnMovies(getReturnMovies())).getStatusCode(), HttpStatus.OK);

        /*Give wrong input with missing parameters*/
        Assert.assertEquals((movieService.returnMovies(getReturnWrongMovies())).getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);

        /*Give correct parameters but with wrong values*/
        Assert.assertEquals((movieService.returnMovies(getReturnWrongValueMovies())).getStatusCode(), HttpStatus.BAD_REQUEST);

    }


    private MovieEntity getMovieEntity() {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(1);
        movieEntity.setName("Matrix");
        movieEntity.setType("new");
        movieEntity.setPrice(30);
        return movieEntity;
    }

    private Movies getMovies() {
        Movies movies = new Movies();
        ArrayList<String> moviesList = new ArrayList<>();
        moviesList.add("Matrix");
        moviesList.add("Spiderman");
        movies.setRent(moviesList);
        return movies;
    }


    private Movies getWrongMovies() {
        Movies movies = new Movies();
        movies.setCurrency("SEK");
        return movies;
    }

    private Movies getWrongValueMovies() {
        Movies movies = new Movies();
        ArrayList<String> moviesList = new ArrayList<>();
        movies.setRent(moviesList);
        return movies;
    }


    private Movies getReturnMovies() {
        Movies movies = new Movies();
        ArrayList<String> moviesList = new ArrayList<>();
        moviesList.add("Matrix");
        moviesList.add("Spiderman");
        movies.setRent(moviesList);
        movies.setDateOfRent("23-03-2023");
        return movies;
    }


    private Movies getReturnWrongMovies() {
        Movies movies = new Movies();
        movies.setCurrency("SEK");
        return movies;
    }

    private Movies getReturnWrongValueMovies() {
        Movies movies = new Movies();
        ArrayList<String> moviesList = new ArrayList<>();
        movies.setRent(moviesList);
        movies.setDateOfRent("23/03/2023");

        return movies;
    }
}