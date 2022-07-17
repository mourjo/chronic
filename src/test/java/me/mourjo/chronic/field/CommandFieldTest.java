package me.mourjo.chronic.field;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandFieldTest {
    @Test
    void parseCommandTest() {
        Field c = new CommandField("/usr/bin/find");
        assertEquals("/usr/bin/find", c.toString());
    }
}