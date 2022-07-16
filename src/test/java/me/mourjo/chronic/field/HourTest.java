package me.mourjo.chronic.field;

import me.mourjo.chronic.exceptions.UnexpectedAtomException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HourTest {
    @Test
    void parseValidHourTest() {
        Hour h = new Hour("1");
        assertEquals("1", h.toString());

        h = new Hour("24");
        assertEquals("24", h.toString());

        h = new Hour("*/15");
        assertEquals("15", h.toString());

        h = new Hour("5-24/20");
        assertEquals("20", h.toString());

        h = new Hour("1-20/15");
        assertEquals("15", h.toString());

        h = new Hour("*/150");
        assertEquals("", h.toString());
    }

    @Test
    void parseInValidHourTest() {
        Hour h = new Hour("88");
        assertThrows(UnexpectedAtomException.class, h::toString);

        h = new Hour("1-100/2");
        assertThrows(UnexpectedAtomException.class, h::toString);

        h = new Hour("1-2,80-90");
        assertThrows(UnexpectedAtomException.class, h::toString);
    }
}