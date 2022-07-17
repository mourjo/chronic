package me.mourjo.chronic.field;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Parent of all numeric types like Minute, Hour. Parses tokens based on the current field's bounds (min/max).
 */
public abstract sealed class NumericField extends Field permits DayOfMonthField, DayOfWeekField, HourField, MinuteField, MonthField {
    protected List<Integer> domain;

    public NumericField(String token) {
        super(token);
    }

    @Override
    public void parse() {
        domain = parser.parse(token);
        isParsed = true;
    }

    @Override
    public String toString() {
        parse();
        return domain.stream().map(Object::toString).collect(Collectors.joining(","));
    }
}
