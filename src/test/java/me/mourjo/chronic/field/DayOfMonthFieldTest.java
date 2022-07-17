package me.mourjo.chronic.field;

import me.mourjo.chronic.exception.UnexpectedAtomException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DayOfMonthFieldTest {

    @Test
    void parseValidDayOfMonthTest() {
        Field dom = new DayOfMonthField("1");
        assertEquals("1", dom.toString());

        dom = new DayOfMonthField("31");
        assertEquals("31", dom.toString());

        dom = new DayOfMonthField("*/15");
        assertEquals("15,30", dom.toString());

        dom = new DayOfMonthField("5-24/20");
        assertEquals("20", dom.toString());

        dom = new HourField("1-20/15");
        assertEquals("15", dom.toString());

        dom = new HourField("*/150");
        assertEquals("0", dom.toString());
    }

    @Test
    void parseInValidDayOfMonthTest() {
        Field dom = new DayOfMonthField("88");
        assertThrows(UnexpectedAtomException.class, dom::toString);

        dom = new DayOfMonthField("1-100/2");
        assertThrows(UnexpectedAtomException.class, dom::toString);

        dom = new DayOfMonthField("0-4,10-31");
        assertThrows(UnexpectedAtomException.class, dom::toString);

        dom = new DayOfMonthField("1-2,80-90");
        assertThrows(UnexpectedAtomException.class, dom::toString);
    }
}