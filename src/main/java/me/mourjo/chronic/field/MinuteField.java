package me.mourjo.chronic.field;

import me.mourjo.chronic.atom.NumericAtomParser;

public final class MinuteField extends NumericField {
    public MinuteField(String token) {
        super(token);
        parser = new NumericAtomParser(0, 59);
    }

    @Override
    public String describe() {
        return "Minute";
    }
}
