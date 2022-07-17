package me.mourjo.chronic.field;

import me.mourjo.chronic.exceptions.UnexpectedAtomException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MinuteTest {
    @Test
    void parseValidMinuteTest() {
        Field m = new Minute("0");
        assertEquals("0", m.toString());

        m = new Minute("59");
        assertEquals("59", m.toString());

        m = new Minute("*/15");
        assertEquals("0,15,30,45", m.toString());

        m = new Minute("25-33/15");
        assertEquals("30", m.toString());

        m = new Minute("25-33,40-59/15");
        assertEquals("30,45", m.toString());

        m = new Minute("*/2500");
        assertEquals("0", m.toString());
    }

    @Test
    void parseInValidMinuteTest() {
        Field m = new Minute("88");
        assertThrows(UnexpectedAtomException.class, m::toString);

        m = new Minute("25-100/2");
        assertThrows(UnexpectedAtomException.class, m::toString);

        m = new Minute("1-2,80-90");
        assertThrows(UnexpectedAtomException.class, m::toString);
    }
}