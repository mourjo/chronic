package me.mourjo.chronic.field;

import me.mourjo.chronic.parser.NumericParser;

/**
 * Parses and stores a DayOfMonth
 */
public final class DayOfMonthField extends NumericField {
    public DayOfMonthField(String token) {
        super(token);
        parser = new NumericParser(1, 31);
    }

    @Override
    public String describe() {
        return "Day of Month";
    }
}
