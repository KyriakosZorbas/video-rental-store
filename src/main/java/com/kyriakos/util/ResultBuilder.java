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
