package me.mourjo.chronic.exception;

public class IncorrectExpressionException extends ChronicException {
    public IncorrectExpressionException(String message) {
        super("Expression is incorrect: " + message);
    }
}
