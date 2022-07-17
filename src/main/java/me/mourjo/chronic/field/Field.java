package me.mourjo.chronic.field;

import me.mourjo.chronic.atom.AtomParser;

public abstract sealed class Field permits Command, NumberField {
    protected String token;
    protected boolean isParsed = false;
    protected AtomParser parser;

    public Field(String token) {
        this.token = token;
    }

    public abstract void parse();

    @Override
    public String toString() {
        return token;
    }
}
