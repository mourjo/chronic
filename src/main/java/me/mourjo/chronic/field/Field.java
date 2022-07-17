package me.mourjo.chronic.field;

import me.mourjo.chronic.parser.NumericParser;

public abstract sealed class Field permits CommandField, NumericField {
    protected String token;
    protected boolean isParsed = false;
    protected NumericParser parser;

    public Field(String token) {
        this.token = token;
    }

    public abstract void parse();

    public abstract String describe();

    @Override
    public String toString() {
        return token;
    }
}
