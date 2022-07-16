package me.mourjo.chronic.field;

public final class Command extends Field {
    public Command(String token) {
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
