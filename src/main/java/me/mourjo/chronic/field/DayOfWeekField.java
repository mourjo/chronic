package me.mourjo.chronic.field;

import me.mourjo.chronic.atom.NumericAtomParser;

public final class DayOfWeekField extends NumericField {
    public DayOfWeekField(String token) {
        super(token);
        parser = new NumericAtomParser(0, 6);
    }
}
