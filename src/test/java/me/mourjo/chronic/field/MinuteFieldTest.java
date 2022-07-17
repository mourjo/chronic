package me.mourjo.chronic.field;

import me.mourjo.chronic.exception.UnexpectedAtomException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MinuteFieldTest {
    @Test
    void parseValidMinuteTest() {
        Field m = new MinuteField("0");
        assertEquals("0", m.toString());

        m = new MinuteField("59");
        assertEquals("59", m.toString());

        m = new MinuteField("*/15");
        assertEquals("0,15,30,45", m.toString());

        m = new MinuteField("25-33/15");
        assertEquals("30", m.toString());

        m = new MinuteField("25-26,40-59/15");
        assertEquals("25,26,45", m.toString());

        m = new MinuteField("*/2500");
        assertEquals("0", m.toString());
    }

    @Test
    void parseInValidMinuteTest() {
        Field m = new MinuteField("88");
        assertThrows(UnexpectedAtomException.class, m::toString);

        m = new MinuteField("25-100/2");
        assertThrows(UnexpectedAtomException.class, m::toString);

        m = new MinuteField("1-2,80-90");
        assertThrows(UnexpectedAtomException.class, m::toString);
    }
}