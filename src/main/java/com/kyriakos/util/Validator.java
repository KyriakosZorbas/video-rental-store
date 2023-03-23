package com.kyriakos.util;

import com.kyriakos.entity.MovieEntity;
import com.kyriakos.exception.RentMoviesApplicationException;
import com.kyriakos.model.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class Validator {

    @Autowired
    RentMoviesApplicationException rentMoviesApplicationException;

    /* Check the provided input if includes all the parameters regarding the Movie model.*/
    public ResponseEntity<String> checkMovieStructure(Movies movies, Boolean returned) {


        if (movies.getRent() == null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(rentMoviesApplicationException.getErrorForMissingParameterExceptionTextMessage("rent"));
        }
        if (movies.getRent().size() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(rentMoviesApplicationException.getErrorForEmptyParameterExceptionTextMessage("rent"));
        }

        if (returned) {
            if (movies.getDateOfRent() == null) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(rentMoviesApplicationException.getErrorForMissingParameterExceptionTextMessage("dateOfRent"));
            }

            if (checkDate(movies.getDateOfRent()) == false) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(rentMoviesApplicationException.getErrorForWrongDateFormatParameterExceptionTextMessage("dateOfRent"));
            }

        }


        return new ResponseEntity<>("OK", HttpStatus.OK);


    }

    /* Check the provided input if includes all the parameters regarding the MovieEntity entity.*/
    public ResponseEntity<String> checkMovieEntityStructure(MovieEntity movieEntity) {

        if (movieEntity.getId() == 0) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(rentMoviesApplicationException.getErrorForMissingParameterExceptionTextMessage("id"));
        }
        if (movieEntity.getName() == null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(rentMoviesApplicationException.getErrorForMissingParameterExceptionTextMessage("name"));
        }
        if (movieEntity.getType() == null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(rentMoviesApplicationException.getErrorForMissingParameterExceptionTextMessage("type"));
        }
        if (movieEntity.getPrice() == null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(rentMoviesApplicationException.getErrorForMissingParameterExceptionTextMessage("price"));
        }

        return new ResponseEntity<>("OK", HttpStatus.OK);

    }

    /* Check if the provided date of rent follows the correct pattern */
    public Boolean checkDate(String dateOfRent) {

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate.parse(dateOfRent, dateTimeFormatter).atStartOfDay();
        } catch (java.time.format.DateTimeParseException dateTimeParseException) {

            return false;
        }

        return true;

    }


}
