package com.kyriakos.exception;

import org.springframework.stereotype.Component;

@Component
public class RentMoviesApplicationException extends Exception {


    public RentMoviesApplicationException() {
    }

    public String getErrorForWrongDateFormatParameterExceptionTextMessage(String parameter) {
        return "{\"error\":\"parameter: " + parameter + " has wrong value. the correct value should be in this format: dd-MM-yyyy (22-03-2023) .\"}";
    }

    public String getErrorForEmptyParameterExceptionTextMessage(String parameter) {
        return "{\"error\":\"parameter: " + parameter + "has empty value.\"}";
    }

    public String getErrorForMissingParameterExceptionTextMessage(String parameter) {
        return "{\"error\":\"parameter: " + parameter + " is mandatory for this request.\"}";
    }

    public String getErrorForMovieNotExistForSpecificIDExceptionTextMessage(Integer id) {
        return "{\"error\":\"The movie with id : " + id + " not exist in our store.\"}";
    }

    public String getErrorForMovieNotExistExceptionTextMessage(String movies) {
        return "{\"error\":\"The following movies : " + movies + " not exist in our store.\"}";
    }

}



