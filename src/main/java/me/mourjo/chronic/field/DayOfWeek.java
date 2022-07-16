package me.mourjo.chronic.field;

public final class DayOfWeek extends Field {
    public DayOfWeek(String token) {
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
