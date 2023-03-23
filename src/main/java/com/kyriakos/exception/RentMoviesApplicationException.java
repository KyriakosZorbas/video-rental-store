package com.kyriakos.exception;

import org.springframework.stereotype.Component;

@Component
public class RentMoviesApplicationException extends Exception {


    public RentMoviesApplicationException() {
    }

    public String getErrorForMovieNotExistForSpecificIDExceptionTextMessage(Integer id) {
        return "{\"error\":\"The movie with id : " + id + " not exist in our store.\"}";
    }


}



