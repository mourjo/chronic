package me.mourjo.chronic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpressionTest {

    @Test
    public void validExpressionTest() {
        Expression e = new Expression("*/15 0 1,15 * 1-5 /usr/bin/find");

        assertEquals("""
                Minute: 0,15,30,45
                Hour: 0
                Day of Month: 1,15
                Month: 1,2,3,4,5,6,7,8,9,10,11,12
                Day of Week: 1,2,3,4,5
                Command: /usr/bin/find
                """, e.toString());

        e = new Expression("25-33,46-59/15 0-23 1-31,7,6 1-12 0-6 /usr/bin/find");

        /*
        https://crontab.guru/#25-33,40-59/15_0-23_1-31,7,6_1-12_0-6
        At every minute from 25 through 33 and every 15th minute from 40 through 59 past every hour from 0 through 23
        on every day-of-month from 1 through 31, 7, and 6 and on every day-of-week from Sunday through Saturday in
        every month from January through December
         */
        assertEquals("""
                Minute: 25,26,27,28,29,30,31,32,33
                Hour: 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23
                Day of Month: 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31
                Month: 1,2,3,4,5,6,7,8,9,10,11,12
                Day of Week: 0,1,2,3,4,5,6
                Command: /usr/bin/find
                """, e.toString());

        /*
        https://crontab.guru/#25-33,40-59/15_0-1_*_*\/4_1-3
        At every minute from 25 through 33 and every 15th minute from 40 through 59 past every hour from 0 through 1
        on every day-of-week from Monday through Wednesday in every 4th month.
         */
        e = new Expression("25-33,40-59/15 0-1 * */4 1-3     /usr/bin/find");
        assertEquals("""
                Minute: 25,26,27,28,29,30,31,32,33,45
                Hour: 0,1
                Day of Month: 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31
                Month: 4,8,12
                Day of Week: 1,2,3
                Command: /usr/bin/find
                """, e.toString());
    }

}