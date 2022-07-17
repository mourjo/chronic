package me.mourjo.chronic.field;

import me.mourjo.chronic.parser.NumericParser;

/**
 * Each part of a cron expression is parsed and stored in a Field. It provides the uniform .parse method to all Fields.
 */
public abstract sealed class Field permits CommandField, NumericField {
    protected String token;
    protected boolean isParsed = false;
    protected NumericParser parser;

    public Field(String token) {
        this.token = token;
    }

    /**
     * Parsing is lazy, until parse is called, tokens are not parsed. Once parsed, calling again is a no-op.
     */
    public abstract void parse();

    /**
     * Description of the field (what should this field be called in the output?)
     *
     * @return name of current field
     */
    public abstract String describe();

    @Override
    public String toString() {
        return token;
    }
}
