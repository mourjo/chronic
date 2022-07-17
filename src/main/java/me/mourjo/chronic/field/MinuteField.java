package me.mourjo.chronic.field;

import me.mourjo.chronic.parser.NumericParser;

public final class MinuteField extends NumericField {
    public MinuteField(String token) {
        super(token);
        parser = new NumericParser(0, 59);
    }

    @Override
    public String describe() {
        return "Minute";
    }
}
