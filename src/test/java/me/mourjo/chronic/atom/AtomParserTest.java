package me.mourjo.chronic.atom;

import me.mourjo.chronic.exceptions.UnexpectedAtomException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AtomParserTest {

    @Test
    void simpleIntegerTest() throws UnexpectedAtomException {
        AtomParser p = new AtomParser(1, 100);
        assertEquals(List.of(13), p.parse("13"));
        assertEquals(List.of(), p.parse("223"));
        assertEquals(List.of(1), p.parse("1"));

        p = new AtomParser(1, 10);
        assertEquals(List.of(), p.parse("13"));
        assertEquals(List.of(), p.parse("223"));
        assertEquals(List.of(1), p.parse("1"));

        p = new AtomParser(1, 1);
        assertEquals(List.of(), p.parse("13"));
        assertEquals(List.of(), p.parse("223"));
        assertEquals(List.of(1), p.parse("1"));
    }

    @Test
    void rangeTest() throws UnexpectedAtomException {
        AtomParser p = new AtomParser(5, 100);
        assertEquals(List.of(13, 14, 15, 16, 17, 18, 19, 20), p.parse("13-20"));
        assertEquals(List.of(98, 99, 100), p.parse("98-200"));
        assertEquals(List.of(5, 6, 7), p.parse("1-7"));
        assertEquals(List.of(), p.parse("101-700"));
    }

    @Test
    void starTest() throws UnexpectedAtomException {
        AtomParser p = new AtomParser(5, 10);
        assertEquals(List.of(5, 6, 7, 8, 9, 10), p.parse("*"));
    }

    @Test
    void filterTest() throws UnexpectedAtomException {
        AtomParser p = new AtomParser(5, 12);
        assertEquals(List.of(6, 8, 10, 12), p.parse("*/2"));
        assertEquals(List.of(6, 8, 10), p.parse("1-10/2"));
        assertEquals(List.of(9), p.parse("1-10/9"));
        assertEquals(List.of(9), p.parse("*/9"));
        assertEquals(List.of(), p.parse("*/13"));
        assertEquals(List.of(), p.parse("4-5/3"));
    }

    @Test
    void selectTest() throws UnexpectedAtomException {
        AtomParser p = new AtomParser(5, 12);
        assertEquals(List.of(7, 8), p.parse("7,8"));
        assertEquals(List.of(), p.parse("1,2,3"));
        assertEquals(List.of(9), p.parse("7,8,9/3"));
        assertEquals(List.of(9, 12), p.parse("7,8,9-12/3"));
        assertEquals(List.of(), p.parse("1-3,5-7,9-11/4"));
        assertEquals(List.of(), p.parse("1-3,5-7,9-11,13-200/4"));
    }

    @Test
    void invalidSyntaxCheckTest() throws UnexpectedAtomException {
        AtomParser p = new AtomParser(5, 12);
        assertThrows(UnexpectedAtomException.class, () -> p.parse("s"));
        assertThrows(UnexpectedAtomException.class, () -> p.parse(","));

        assertThrows(UnexpectedAtomException.class, () -> p.parse("/"));
        assertThrows(UnexpectedAtomException.class, () -> p.parse("//"));
        assertThrows(UnexpectedAtomException.class, () -> p.parse("/1"));
        assertThrows(UnexpectedAtomException.class, () -> p.parse("2/"));
        assertThrows(UnexpectedAtomException.class, () -> p.parse("h/"));
        assertThrows(UnexpectedAtomException.class, () -> p.parse("/y"));
        assertThrows(UnexpectedAtomException.class, () -> p.parse("/y"));

        assertThrows(UnexpectedAtomException.class, () -> p.parse("-"));
        assertThrows(UnexpectedAtomException.class, () -> p.parse("1-"));
        assertThrows(UnexpectedAtomException.class, () -> p.parse("-1"));
        assertThrows(UnexpectedAtomException.class, () -> p.parse("-s"));
        assertThrows(UnexpectedAtomException.class, () -> p.parse("--"));


        assertThrows(UnexpectedAtomException.class, () -> p.parse("**"));
        assertThrows(UnexpectedAtomException.class, () -> p.parse("*-*"));
        assertThrows(UnexpectedAtomException.class, () -> p.parse("*/*"));
        assertThrows(UnexpectedAtomException.class, () -> p.parse("*h*"));

    }
}