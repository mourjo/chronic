package me.mourjo.chronic.field;

public final class Minute extends Field {
    public Minute(String token) {
        super(token);
    }

    @Override
    public void parse() {
        isParsed = true;
    }

    @Override
    public String toString() {
        parse();
        return super.toString();
    }
}
