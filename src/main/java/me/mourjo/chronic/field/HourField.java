package me.mourjo.chronic.field;

import me.mourjo.chronic.parser.NumericParser;

/**
 * Parses and stores an Hour
 */
public final class HourField extends NumericField {
    public HourField(String token) {
        super(token);
        parser = new NumericParser(0, 23);
    }

    @Override
    public String describe() {
        return "Hour";
    }
}
