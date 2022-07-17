package me.mourjo.chronic.field;

import me.mourjo.chronic.atom.NumericAtomParser;

public final class MonthField extends NumericField {
    public MonthField(String token) {
        super(token);
        parser = new NumericAtomParser(1, 12);
    }

    @Override
    public String describe() {
        return "Month";
    }
}
