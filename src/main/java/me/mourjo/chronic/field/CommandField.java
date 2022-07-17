package me.mourjo.chronic.field;


/**
 * Stores a Command
 */
public final class CommandField extends Field {
    public CommandField(String token) {
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

    @Override
    public String describe() {
        return "Command";
    }
}
