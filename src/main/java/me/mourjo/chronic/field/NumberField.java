package me.mourjo.chronic.field;

import java.util.List;
import java.util.stream.Collectors;

public abstract sealed class NumberField extends Field permits DayOfMonth, DayOfWeek, Hour, Minute, Month {
    protected List<Integer> domain;

    public NumberField(String token) {
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
