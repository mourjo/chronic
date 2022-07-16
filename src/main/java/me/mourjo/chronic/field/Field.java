package me.mourjo.chronic.field;

public abstract sealed class Field permits Command, DayOfMonth, DayOfWeek, Hour, Minute, Month {
    protected String token;
    protected boolean isParsed = false;

    public Field(String token) {
        this.token = token;
    }

    public abstract void parse();

}
