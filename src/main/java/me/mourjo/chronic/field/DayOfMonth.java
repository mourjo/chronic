package me.mourjo.chronic.field;

public final class DayOfMonth extends Field {
    public DayOfMonth(String token) {
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
