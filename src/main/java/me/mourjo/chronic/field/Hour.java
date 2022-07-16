package me.mourjo.chronic.field;

public final class Hour extends Field {
    public Hour(String token) {
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
