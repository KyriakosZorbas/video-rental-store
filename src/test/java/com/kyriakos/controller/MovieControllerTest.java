package com.kyriakos.controller;

import com.kyriakos.Application;
import com.kyriakos.entity.MovieEntity;
import com.kyriakos.repository.MovieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class MovieControllerTest {

    String ReturnMovies = "/api/movies/return";

    String RentMovies = "/api/movies/rent";

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;


    /* TEST endpoint that used for return movies */
    /* Endpoint POST /api/movies/rent */
    @Test
    public void testRentMovies() throws Exception {

        MovieEntity movieEntity = getMovieEntity();
        movieRepository.save(movieEntity);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        /* Sent request with proper parameters and values. */
        String postBody = "{\"rent\": [\"Matrix\"]}\n";
        testNormalBehavior(postBody, RentMovies);

        /* Sent request with correct parameters and wrong values. */
        postBody = "{\"rent\": [\"MOVIE THAT NOT EXIST\"]}\n";
        testCorrectParametersWrongValueForReturnMovies(postBody, RentMovies);


    }


    /* TEST endpoint that used for return movies */
    /* Endpoint POST /api/movies/return */
    @Test
    public void testReturnMovies() throws Exception {

        MovieEntity movieEntity = getMovieEntity();
        movieRepository.save(movieEntity);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        /* Sent request with proper parameters and values. */
        String postBody = "{\"rent\": [\"Matrix\"],\"dateOfRent\":\"15-03-2023\"}\n";
        testNormalBehavior(postBody, ReturnMovies);

        /* Sent request with correct parameters and wrong values. */
        postBody = "{\"rent\": [\"\"],\"dateOfRent\":\"15-03-2023\"}\n";
        testCorrectParametersWrongValueForReturnMovies(postBody, ReturnMovies);


    }

    public void testNormalBehavior(String postBody, String endpoint) throws Exception {

        this.mockMvc.perform(post(endpoint)
                        .content(postBody)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public void testCorrectParametersWrongValueForReturnMovies(String postBody, String endpoint) throws Exception {

        this.mockMvc.perform(post(endpoint)
                        .content(postBody)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }


    private MovieEntity getMovieEntity() {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(1);
        movieEntity.setName("Matrix");
        movieEntity.setType("new");
        movieEntity.setPrice(30);
        return movieEntity;
    }
}


