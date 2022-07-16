package me.mourjo.chronic.field;

import me.mourjo.chronic.atom.AtomParser;

public final class Hour extends NumberField {
    public Hour(String token) {
        super(token);
        parser = new AtomParser(1, 24);
    }
}
