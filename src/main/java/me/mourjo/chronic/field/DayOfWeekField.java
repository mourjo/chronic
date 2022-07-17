package me.mourjo.chronic.field;

import me.mourjo.chronic.parser.NumericParser;

/**
 * Parses and stores a DayOfWeek
 */
public final class DayOfWeekField extends NumericField {
    public DayOfWeekField(String token) {
        super(token);
        parser = new NumericParser(0, 6);
    }

    @Override
    public String describe() {
        return "Day of Week";
    }
}
