package me.mourjo.chronic.field;

import me.mourjo.chronic.atom.NumericAtomParser;

public abstract sealed class Field permits CommandField, NumericField {
    protected String token;
    protected boolean isParsed = false;
    protected NumericAtomParser parser;

    public Field(String token) {
        this.token = token;
    }

    public abstract void parse();

    @Override
    public String toString() {
        return token;
    }
}
