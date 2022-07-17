package me.mourjo.chronic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    }

}