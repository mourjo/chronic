package me.mourjo.chronic.field;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {
    @Test
    void parseCommandTest() {
        Field c = new Command("/usr/bin/find");
        assertEquals("/usr/bin/find", c.toString());
    }
}