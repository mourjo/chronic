package me.mourjo.chronic;

import me.mourjo.chronic.exception.IncorrectExpressionException;
import me.mourjo.chronic.exception.UnexpectedAtomException;

import java.util.Arrays;

public class Launcher {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Only one argument is expected, got: " + Arrays.toString(args));
        }

        try {
            Expression e = new Expression(args[0]);
            System.out.println(e);
        } catch (IncorrectExpressionException | UnexpectedAtomException e) {
            System.out.println("Invalid cron: " + args[0] + "\n" + e.getMessage());
        }
    }
}
