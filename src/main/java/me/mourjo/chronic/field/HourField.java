package me.mourjo.chronic.field;

import me.mourjo.chronic.atom.NumericAtomParser;

public final class HourField extends NumericField {
    public HourField(String token) {
        super(token);
        parser = new NumericAtomParser(0, 23);
    }

    @Override
    public String describe() {
        return "Hour";
    }
}
