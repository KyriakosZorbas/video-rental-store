package com.kyriakos.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyriakos.entity.MovieEntity;
import com.kyriakos.model.Movie;
import com.kyriakos.model.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

@Component
public class ResultBuilder {

    @Autowired
    Movie movie;

    @Autowired
    Helper helper;

    public String buildResultForRentMovies(ArrayList<MovieEntity> movieEntities) throws JsonProcessingException {

        Movies movies = new Movies();
        ArrayList<Movie> moviesList = new ArrayList<>();
        int moviesTotalPrice = 0;


        Iterator<MovieEntity> it = movieEntities.iterator();
        while (it.hasNext()) {

            MovieEntity movieEntity = it.next();

            Movie tempMovie = new Movie();
            tempMovie.setName(movieEntity.getName());
            tempMovie.setPrice(movieEntity.getPrice());
            tempMovie.setType(movieEntity.getType());
            tempMovie.setDelay(0);
            moviesList.add(tempMovie);
            moviesTotalPrice = moviesTotalPrice + movieEntity.getPrice();

        }

        movies.setMovies(moviesList);
        movies.setTotalPrice(moviesTotalPrice);
        movies.setCurrency("SEK");

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(movies);

        return result;
    }

    public String buildResultForReturnMovies(ArrayList<MovieEntity> movieEntities, String dateOfRent) throws JsonProcessingException, ParseException {

        Movies movies = new Movies();
        ArrayList<Movie> moviesList = new ArrayList<>();
        String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        int moviesTotalPrice = 0;


        Iterator<MovieEntity> it = movieEntities.iterator();
        while (it.hasNext()) {

            MovieEntity movieEntity = it.next();

            Movie tempMovie = new Movie();
            tempMovie.setName(movieEntity.getName());
            tempMovie.setType(movieEntity.getType());
            int delay = helper.calculateDelay(dateOfRent,dateNow,movieEntity.getType());
            tempMovie.setDelay(delay);
            int price = helper.calculatePrice(delay, movieEntity.getPrice());
            tempMovie.setPrice(price);

            moviesList.add(tempMovie);
            moviesTotalPrice = moviesTotalPrice + price;

        }

        movies.setMovies(moviesList);
        movies.setTotalPrice(moviesTotalPrice);
        movies.setCurrency("SEK");

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(movies);

        return result;
    }

    public String buildResultForGetMovieByID(MovieEntity movieEntity) throws JsonProcessingException {

        return getString(movieEntity);
    }

    public String buildResultForAddMovie(MovieEntity movieEntity) throws JsonProcessingException {

        String result = "{\"info\":\"The following movie has been created.\",\"movie\":"+getString(movieEntity)+"}";
        return result;
    }

    private String getString(MovieEntity movieEntity) throws JsonProcessingException {

        movie.setId(movieEntity.getId());
        movie.setName(movieEntity.getName());
        movie.setType(movieEntity.getType());
        movie.setPrice(movieEntity.getPrice());

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(movie);

        return result;
    }
}
