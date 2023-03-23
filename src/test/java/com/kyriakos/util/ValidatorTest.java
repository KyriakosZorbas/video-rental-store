package com.kyriakos.util;

import com.kyriakos.Application;
import com.kyriakos.entity.MovieEntity;
import com.kyriakos.model.Movie;
import com.kyriakos.model.Movies;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = Application.class)
public class ValidatorTest {

    @Autowired
    Validator validator;

    @Autowired
    Movie movie;


    /* Check the provided input if includes all the parameters regarding the Movie model.*/
    @Test
    public void testCheckMovieStructure() {

        /*check movie structure for rent movies*/
        Movies movies = getMovies();
        Assert.assertEquals(validator.checkMovieStructure(movies, false).getStatusCode(), HttpStatus.OK);

        /*check movie structure for wrong rent movies input */
        Movies wrongMovies = getWrongMovies();
        Assert.assertEquals(validator.checkMovieStructure(wrongMovies, false).getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);

        /*check movie structure for correct rent movies input but with wrong parameter values */
        Movies wrongParametersValueMovies = getWrongParametersValueMovies();
        Assert.assertEquals(validator.checkMovieStructure(wrongParametersValueMovies, false).getStatusCode(), HttpStatus.BAD_REQUEST);


        /*check movie structure for return movies*/
        Movies returnMovies = getReturnMovies();
        Assert.assertEquals(validator.checkMovieStructure(returnMovies, true).getStatusCode(), HttpStatus.OK);

        /*check movie structure for wrong return movies input */
        Movies returnWrongMovies = getReturnWrongMovies();
        Assert.assertEquals(validator.checkMovieStructure(returnWrongMovies, true).getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);

        /*check movie structure for correct return movies input but with wrong parameter values */
        Movies returnWrongParametersValueMovies = getReturnWrongParametersValueMovies();
        Assert.assertEquals(validator.checkMovieStructure(returnWrongParametersValueMovies, true).getStatusCode(), HttpStatus.BAD_REQUEST);

    }

    /* Check the provided input if includes all the parameters regarding the MovieEntity entity.*/
    @Test
    public void testCheckMovieEntityStructure() {

        MovieEntity movieEntity = getMovieEntity();
        Assert.assertEquals(validator.checkMovieEntityStructure(movieEntity).getStatusCode(), HttpStatus.OK);

        /* Create a wrong movie entity in order to check if the correct response code is return. */
        MovieEntity wrongMovieEntity = getWrongMovieEntity();
        Assert.assertEquals(validator.checkMovieEntityStructure(wrongMovieEntity).getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);

    }

    /* Check if the provided date has the correct pattern */
    @Test
    public void testCheckDate() {

        String date = "22-03-2023";
        Assert.assertTrue(validator.checkDate(date));

        date = "22/03/2023";
        Assert.assertFalse(validator.checkDate(date));

        date = "03-22-2023";
        Assert.assertFalse(validator.checkDate(date));

    }


    private MovieEntity getMovieEntity() {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(1);
        movieEntity.setName("Matrix");
        movieEntity.setType("new");
        movieEntity.setPrice(30);
        return movieEntity;
    }

    private MovieEntity getWrongMovieEntity() {
        MovieEntity movieEntity = new MovieEntity();
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

        movies.setCurrency("EUR");
        return movies;
    }

    private Movies getWrongParametersValueMovies() {

        Movies movies = new Movies();
        ArrayList<String> moviesList = new ArrayList<>();
        movies.setRent(moviesList);

        return movies;
    }


    private Movies getReturnMovies() {

        Movies movies = new Movies();
        ArrayList<String> moviesList = new ArrayList<>();
        moviesList.add("Matrix");

        movies.setRent(moviesList);
        movies.setDateOfRent("23-03-2023");

        return movies;
    }


    private Movies getReturnWrongMovies() {

        Movies movies = new Movies();
        ArrayList<Movie> moviesList = new ArrayList<>();

        movie.setName("Superman");
        movie.setType("Regular");
        movie.setDelay(0);
        movie.setPrice(30);

        moviesList.add(movie);
        movies.setMovies(moviesList);

        return movies;
    }


    private Movies getReturnWrongParametersValueMovies() {

        Movies movies = new Movies();
        ArrayList<String> moviesList = new ArrayList<>();
        moviesList.add("Matrix");

        movies.setRent(moviesList);
        movies.setDateOfRent("23/03/2023");

        return movies;
    }

}
