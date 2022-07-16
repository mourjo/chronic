package me.mourjo.chronic.field;

import me.mourjo.chronic.atom.AtomParser;

public final class Minute extends NumberField {
    public Minute(String token) {
        super(token);
        parser = new AtomParser(0, 59);
    }
}
