package me.mourjo.chronic.exception;

public class ChronicException extends RuntimeException {

    public ChronicException(String s) {
        super(s);
    }

    public ChronicException(String s, Throwable t) {
        super(s, t);
    }
}
