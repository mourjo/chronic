package me.mourjo.chronic.field;

import me.mourjo.chronic.exceptions.UnexpectedAtomException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DayOfWeekTest {

    @Test
    void parseValidDayOfWeekTest() {
        Field dow = new DayOfWeek("0");
        assertEquals("0", dow.toString());

        dow = new DayOfWeek("6");
        assertEquals("6", dow.toString());

        dow = new DayOfWeek("*/2");
        assertEquals("0,2,4,6", dow.toString());

        dow = new DayOfWeek("1-6/3");
        assertEquals("3,6", dow.toString());

        dow = new DayOfWeek("1-6");
        assertEquals("1,2,3,4,5,6", dow.toString());

        dow = new DayOfWeek("*");
        assertEquals("0,1,2,3,4,5,6", dow.toString());

        dow = new Hour("*/150");
        assertEquals("", dow.toString());
    }

    @Test
    void parseInValidDayOfWeekTest() {
        Field dow = new DayOfWeek("7");
        assertThrows(UnexpectedAtomException.class, dow::toString);

        dow = new DayOfWeek("1-100/2");
        assertThrows(UnexpectedAtomException.class, dow::toString);

        dow = new DayOfWeek("1-7");
        assertThrows(UnexpectedAtomException.class, dow::toString);

        dow = new DayOfWeek("0-4,10-31");
        assertThrows(UnexpectedAtomException.class, dow::toString);

        dow = new DayOfWeek("1-2,80-90");
        assertThrows(UnexpectedAtomException.class, dow::toString);
    }

}