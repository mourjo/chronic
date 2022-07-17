package me.mourjo.chronic.field;

import me.mourjo.chronic.atom.NumericAtomParser;

public final class HourField extends NumericField {
    public HourField(String token) {
        super(token);
        parser = new NumericAtomParser(1, 24);
    }
}
