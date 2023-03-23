package com.kyriakos.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class Helper {

    enum MovieType {
        NEW,
        REGULAR,
        OLD
    }


    /*Calculates the total delay for a movie that has been returned.
    *
    *The store has three types of films.
    * 1. NEW films– For each day rented, the <premium price> is charged.
    * 2. REGULAR films – A flat price of <basic price> is charged for the 3 days, and then for each additional day <basic price> is charged per day.
    * 3. OLD films – A flat price of <basic price> is charged for the first 5 days, and then for each additional day <basic price> is charged per day.
    * <premium price> is 40 SEK
    * <basic price> is 30 SEK
    *
    * */
    public int calculateDelay(String dateOfRent,String dateNow,String type){

        MovieType movieType = MovieType.valueOf(type.toUpperCase());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime startDate = LocalDate.parse(dateOfRent, dateTimeFormatter).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(dateNow, dateTimeFormatter).atStartOfDay();
        int daysBetween = Math.toIntExact(Duration.between(startDate, endDate).toDays());
        int delay = 0;

        switch(movieType) {
            case NEW:
                if(daysBetween >1){
                    delay = daysBetween - 1;
                }else{
                    delay = 0;
                }

                break;
            case REGULAR:
                if(daysBetween >3){
                    delay = daysBetween - 3;
                }else{
                    delay = 0;
                }

                break;
            case OLD:
                if(daysBetween >5){
                    delay = daysBetween - 5;
                }else{
                    delay = 0;
                }
                break;
        }
        return delay;
    }


    /* Calculate the price for a movie regarding its delay*/
    public int calculatePrice(Integer delay, Integer price){

        int priceAfterDelay = delay * price;

        return priceAfterDelay;
    }
}
