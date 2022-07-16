package me.mourjo.chronic.field;

import me.mourjo.chronic.atom.AtomParser;

public final class DayOfWeek extends NumberField {
    public DayOfWeek(String token) {
        super(token);
        parser = new AtomParser(0, 6);
    }
}
