package me.mourjo.chronic;

import java.util.Arrays;

public class Launcher {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Only one argument is expected, got: " + Arrays.toString(args));
        }

        Expression e = new Expression(args[0]);
        System.out.println(e);
    }
}
