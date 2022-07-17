package me.mourjo.chronic.field;

import me.mourjo.chronic.exception.UnexpectedAtomException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HourFieldTest {
    @Test
    void parseValidHourTest() {
        Field h = new HourField("0");
        assertEquals("0", h.toString());

        h = new HourField("23");
        assertEquals("23", h.toString());

        h = new HourField("*/12");
        assertEquals("0,12", h.toString());

        h = new HourField("5-23/20");
        assertEquals("20", h.toString());

        h = new HourField("1-20/15");
        assertEquals("15", h.toString());

        h = new HourField("*/150");
        assertEquals("0", h.toString());
    }

    @Test
    void parseInValidHourTest() {
        Field h = new HourField("88");
        assertThrows(UnexpectedAtomException.class, h::toString);

        h = new HourField("1-100/2");
        assertThrows(UnexpectedAtomException.class, h::toString);

        h = new HourField("1-2,80-90");
        assertThrows(UnexpectedAtomException.class, h::toString);
    }
}