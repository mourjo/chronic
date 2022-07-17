package me.mourjo.chronic.field;

import me.mourjo.chronic.exception.UnexpectedAtomException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HourFieldTest {
    @Test
    void parseValidHourTest() {
        Field h = new HourField("1");
        assertEquals("1", h.toString());

        h = new HourField("24");
        assertEquals("24", h.toString());

        h = new HourField("*/15");
        assertEquals("15", h.toString());

        h = new HourField("5-24/20");
        assertEquals("20", h.toString());

        h = new HourField("1-20/15");
        assertEquals("15", h.toString());

        h = new HourField("*/150");
        assertEquals("", h.toString());
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