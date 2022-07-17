package me.mourjo.chronic.field;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandTest {
    @Test
    void parseCommandTest() {
        Field c = new Command("/usr/bin/find");
        assertEquals("/usr/bin/find", c.toString());
    }
}