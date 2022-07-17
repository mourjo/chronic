package me.mourjo.chronic.field;

import me.mourjo.chronic.exceptions.UnexpectedAtomException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DayOfMonthTest {

    @Test
    void parseValidDayOfMonthTest() {
        Field dom = new DayOfMonth("1");
        assertEquals("1", dom.toString());

        dom = new DayOfMonth("31");
        assertEquals("31", dom.toString());

        dom = new DayOfMonth("*/15");
        assertEquals("15,30", dom.toString());

        dom = new DayOfMonth("5-24/20");
        assertEquals("20", dom.toString());

        dom = new Hour("1-20/15");
        assertEquals("15", dom.toString());

        dom = new Hour("*/150");
        assertEquals("", dom.toString());
    }

    @Test
    void parseInValidDayOfMonthTest() {
        Field dom = new DayOfMonth("88");
        assertThrows(UnexpectedAtomException.class, dom::toString);

        dom = new DayOfMonth("1-100/2");
        assertThrows(UnexpectedAtomException.class, dom::toString);

        dom = new DayOfMonth("0-4,10-31");
        assertThrows(UnexpectedAtomException.class, dom::toString);

        dom = new DayOfMonth("1-2,80-90");
        assertThrows(UnexpectedAtomException.class, dom::toString);
    }

}