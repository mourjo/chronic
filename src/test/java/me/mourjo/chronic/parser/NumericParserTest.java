package me.mourjo.chronic.parser;

import me.mourjo.chronic.exception.UnexpectedAtomException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NumericParserTest {

    @Test
    void simpleIntegerTest() throws UnexpectedAtomException {
        NumericParser p = new NumericParser(1, 100);
        assertEquals(List.of(13), p.parse("13"));
        assertEquals(List.of(1), p.parse("1"));
    }

    @Test
    void rangeTest() throws UnexpectedAtomException {
        NumericParser p = new NumericParser(5, 100);
        assertEquals(List.of(13, 14, 15, 16, 17, 18, 19, 20), p.parse("13-20"));
        assertEquals(List.of(5, 6, 7), p.parse("5-7"));

        p = new NumericParser(5, 10);
        assertEquals(List.of(5, 6, 7, 8, 9, 10), p.parse("5-9,5-8,5-7,5-6,5-10"));
    }

    @Test
    void starTest() throws UnexpectedAtomException {
        NumericParser p = new NumericParser(5, 10);
        assertEquals(List.of(5, 6, 7, 8, 9, 10), p.parse("*"));
    }

    @Test
    void filterTest() throws UnexpectedAtomException {
        NumericParser p = new NumericParser(5, 12);
        assertEquals(List.of(6, 8, 10, 12), p.parse("*/2"));
        assertEquals(List.of(9), p.parse("*/9"));
        assertEquals(List.of(), p.parse("*/13"));
        assertEquals(List.of(), p.parse("5-5/3"));
    }

    @Test
    void selectTest() throws UnexpectedAtomException {
        NumericParser p = new NumericParser(5, 12);
        assertEquals(List.of(7, 8), p.parse("7,8"));
        assertEquals(List.of(7, 8, 9), p.parse("7,8,9/3"));
        assertEquals(List.of(7, 8, 9, 12), p.parse("7,8,9-12/3"));
        assertEquals(List.of(5, 6, 7), p.parse("5-7,9-11/4"));
    }

    @Test
    void combinedTest() throws UnexpectedAtomException {
        NumericParser p = new NumericParser(1, 10);
        assertEquals(List.of(3, 4, 5, 8), p.parse("3-5,8-10/4"));

        p = new NumericParser(0, 59);
        assertEquals(List.of(25, 26, 27, 28, 29, 30, 31, 32, 33, 45), p.parse("25-33,43-59/15"));
    }

    @Test
    void invalidSyntaxCheckTest() throws UnexpectedAtomException {
        NumericParser p = new NumericParser(5, 12);
        checkUnexpectedAtom(p, "101-700");

        checkUnexpectedAtom(p, "223");
        checkUnexpectedAtom(p, "1-3,5-7,9-11,13-200/4");
        checkUnexpectedAtom(p, "1,2,3");
        checkUnexpectedAtom(p, "1-10/2");

        checkUnexpectedAtom(p, "s");
        checkUnexpectedAtom(p, ",");
        checkUnexpectedAtom(p, "/");
        checkUnexpectedAtom(p, "//");
        checkUnexpectedAtom(p, "/1/");
        checkUnexpectedAtom(p, "/1");
        checkUnexpectedAtom(p, "2/");
        checkUnexpectedAtom(p, "h/");
        checkUnexpectedAtom(p, "/y");
        checkUnexpectedAtom(p, "/y");

        checkUnexpectedAtom(p, "-");
        checkUnexpectedAtom(p, "1-");
        checkUnexpectedAtom(p, "-1");
        checkUnexpectedAtom(p, "-s");
        checkUnexpectedAtom(p, "--");
        checkUnexpectedAtom(p, "-2-");

        checkUnexpectedAtom(p, "**");
        checkUnexpectedAtom(p, "*-*");
        checkUnexpectedAtom(p, "*/*");
        checkUnexpectedAtom(p, "*h*");

        checkUnexpectedAtom(p, "5-6, 8-10");
    }

    private void checkUnexpectedAtom(NumericParser p, String atom) {
        assertThrows(UnexpectedAtomException.class, () -> p.parse(atom));
    }
}