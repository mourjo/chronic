package me.mourjo.chronic.field;

import me.mourjo.chronic.exception.UnexpectedAtomException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MonthFieldTest {

    @Test
    void parseValidDayOfMonthTest() {
        Field month = new MonthField("1");
        assertEquals("1", month.toString());

        month = new MonthField("12");
        assertEquals("12", month.toString());

        month = new MonthField("*/3");
        assertEquals("3,6,9,12", month.toString());

        month = new MonthField("5-10/2");
        assertEquals("6,8,10", month.toString());

        month = new MonthField("1-12/15");
        assertEquals("", month.toString());

        month = new MonthField("*/150");
        assertEquals("", month.toString());
    }

    @Test
    void parseInValidDayOfMonthTest() {
        Field month = new MonthField("88");
        assertThrows(UnexpectedAtomException.class, month::toString);

        month = new MonthField("1-100/2");
        assertThrows(UnexpectedAtomException.class, month::toString);

        month = new MonthField("1-4,10-31");
        assertThrows(UnexpectedAtomException.class, month::toString);

        month = new MonthField("0-4,10-12");
        assertThrows(UnexpectedAtomException.class, month::toString);

        month = new MonthField("1-2,80-90");
        assertThrows(UnexpectedAtomException.class, month::toString);
    }
}
