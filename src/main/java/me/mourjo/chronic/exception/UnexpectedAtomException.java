package me.mourjo.chronic.exception;

public class UnexpectedAtomException extends RuntimeException {
    public UnexpectedAtomException(String message) {
        super("Unexpected atom: " + message);
    }

    public UnexpectedAtomException(String message, Throwable cause) {
        super("Unexpected atom: " + message, cause);
    }
}
