package me.mourjo.chronic.field;

import me.mourjo.chronic.atom.NumericAtomParser;

public final class DayOfMonthField extends NumericField {
    public DayOfMonthField(String token) {
        super(token);
        parser = new NumericAtomParser(1, 31);
    }
}
