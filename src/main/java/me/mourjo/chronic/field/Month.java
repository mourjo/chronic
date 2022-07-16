package me.mourjo.chronic.field;

import me.mourjo.chronic.atom.AtomParser;

public final class Month extends NumberField {
    public Month(String token) {
        super(token);
        parser = new AtomParser(1, 12);
    }
}
