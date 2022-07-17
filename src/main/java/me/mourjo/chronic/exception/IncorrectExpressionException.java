package me.mourjo.chronic.exception;

public class IncorrectExpressionException extends RuntimeException {
    public IncorrectExpressionException(String message) {
        super("Expression is incorrect: " + message);
    }
}
