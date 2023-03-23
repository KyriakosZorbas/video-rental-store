package com.kyriakos.util;

import com.kyriakos.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = Application.class)
public class HelperTest {

    @Autowired
    Helper helper;

    /*Test that calculates the total delay for a movie that has been returned.
     *
     *The store has three types of films.
     * 1. NEW films– For each day rented, the <premium price> is charged.
     * 2. REGULAR films – A flat price of <basic price> is charged for the 3 days, and then for each additional day <basic price> is charged per day.
     * 3. OLD films – A flat price of <basic price> is charged for the first 5 days, and then for each additional day <basic price> is charged per day.
     * <premium price> is 40 SEK
     * <basic price> is 30 SEK
     *
     * Regarding that the Delay is calculated based on type of the film.
     *
     * */
    @Test
    public void testCalculateDelay() {

        String dateOfRent ="22-03-2023";


        /* Calculate delay for **new** movies if the movie is returned 1 day after the date of rent. */

        String dateNow="23-03-2023";
        String type ="new";
        Integer actualDelay = helper.calculateDelay(dateOfRent,dateNow,type);
        Integer expectedDelay = 0;

        Assert.assertEquals(actualDelay,expectedDelay);

        /* Calculate delay for **old** movies if the movie is returned 1 day after the date of rent. */

        dateNow="23-03-2023";
        type ="regular";
        actualDelay = helper.calculateDelay(dateOfRent,dateNow,type);
        expectedDelay = 0;

        Assert.assertEquals(actualDelay,expectedDelay);

        /* Calculate delay for **regular** movies if the movie is returned 1 day after the date of rent. */

        dateNow="23-03-2023";
        type ="old";
        actualDelay = helper.calculateDelay(dateOfRent,dateNow,type);
        expectedDelay = 0;

        Assert.assertEquals(actualDelay,expectedDelay);

        /* Calculate delay for **new** movies if the movie is returned 3 days after the date of rent. */

        dateNow="25-03-2023";
        type ="new";
        actualDelay = helper.calculateDelay(dateOfRent,dateNow,type);
        expectedDelay = 2;

        Assert.assertEquals(actualDelay,expectedDelay);

        /* Calculate delay for **old** movies if the movie is returned 3 days after the date of rent. */

        dateNow="25-03-2023";
        type ="regular";
        actualDelay = helper.calculateDelay(dateOfRent,dateNow,type);
        expectedDelay = 0;

        Assert.assertEquals(actualDelay,expectedDelay);

        /* Calculate delay for **regular** movies if the movie is returned 3 days after the date of rent. */

        dateNow="25-03-2023";
        type ="old";
        actualDelay = helper.calculateDelay(dateOfRent,dateNow,type);
        expectedDelay = 0;

        Assert.assertEquals(actualDelay,expectedDelay);

        /* Calculate delay for **new** movies if the movie is returned 4 days after the date of rent. */

        dateNow="26-03-2023";
        type ="new";
        actualDelay = helper.calculateDelay(dateOfRent,dateNow,type);
        expectedDelay = 3;

        Assert.assertEquals(actualDelay,expectedDelay);

        /* Calculate delay for **old** movies if the movie is returned 4 days after the date of rent. */

        dateNow="26-03-2023";
        type ="regular";
        actualDelay = helper.calculateDelay(dateOfRent,dateNow,type);
        expectedDelay = 1;

        Assert.assertEquals(actualDelay,expectedDelay);

        /* Calculate delay for **regular** movies if the movie is returned 4 days after the date of rent. */

        dateNow="26-03-2023";
        type ="old";
        actualDelay = helper.calculateDelay(dateOfRent,dateNow,type);
        expectedDelay = 0;

        Assert.assertEquals(actualDelay,expectedDelay);

        /* Calculate delay for **new** movies if the movie is returned 6 days after the date of rent. */

        dateNow="28-03-2023";
        type ="new";
        actualDelay = helper.calculateDelay(dateOfRent,dateNow,type);
        expectedDelay = 5;

        Assert.assertEquals(actualDelay,expectedDelay);

        /* Calculate delay for **old** movies if the movie is returned 6 days after the date of rent. */

        dateNow="28-03-2023";
        type ="regular";
        actualDelay = helper.calculateDelay(dateOfRent,dateNow,type);
        expectedDelay = 3;

        Assert.assertEquals(actualDelay,expectedDelay);

        /* Calculate delay for **regular** movies if the movie is returned 6 days after the date of rent. */

        dateNow="28-03-2023";
        type ="old";
        actualDelay = helper.calculateDelay(dateOfRent,dateNow,type);
        expectedDelay = 1;

        Assert.assertEquals(actualDelay,expectedDelay);


    }


    @Test
    public void testCalculatePrice() {

        int delay = 3;
        int price = 30;
        int expectedPrice = 90; /* 3x30=90 */
        int actualPrice = helper.calculatePrice(delay,price); /* 3x30=90 */

        Assert.assertEquals(expectedPrice,actualPrice);

    }


}
