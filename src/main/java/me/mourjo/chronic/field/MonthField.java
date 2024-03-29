package me.mourjo.chronic.field;

import me.mourjo.chronic.parser.NumericParser;

/**
 * Parses and stores a Month
 */
public final class MonthField extends NumericField {
    public MonthField(String token) {
        super(token);
        parser = new NumericParser(1, 12);
    }

    @Override
    public String describe() {
        return "Month";
    }
}
