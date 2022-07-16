package me.mourjo.chronic.field;

import me.mourjo.chronic.atom.AtomParser;

public final class DayOfMonth extends NumberField {
    public DayOfMonth(String token) {
        super(token);
        parser = new AtomParser(1, 31);
    }
}
