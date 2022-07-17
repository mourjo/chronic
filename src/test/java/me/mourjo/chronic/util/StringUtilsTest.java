package me.mourjo.chronic.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringUtilsTest {
    @Test
    void tokenizeTest() {
        assertEquals(List.of(
                        "*/15",
                        "0",
                        "1,15",
                        "*",
                        "1-5",
                        "/usr/bin/find"),
                StringUtils.tokenize("*/15 0 1,15 * 1-5 /usr/bin/find"));

        assertEquals(List.of(
                        "*/15",
                        "0",
                        "1,15",
                        "*",
                        "1-5",
                        "/usr/bin/find"),
                StringUtils.tokenize("      */15     0     1,15      *      1-5     /usr/bin/find  "));
    }
}