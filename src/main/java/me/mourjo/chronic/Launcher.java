package me.mourjo.chronic;

import me.mourjo.chronic.exception.ChronicException;

import java.util.Arrays;

/**
 * Entry point to the CLI, call this class directly with the cron string as one argument
 * <p>
 * Example CLI usage:
 * java -jar target/chronic.jar "* 0 1,15 * 1-5 /usr/bin/find"
 */
public class Launcher {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Only one argument is expected, got: " + Arrays.toString(args));
        }

        try {
            Expression expression = new Expression(args[0]);
            System.out.println(expression);
        } catch (ChronicException e) {
            System.out.println("Invalid cron: " + args[0] + "\n" + e.getMessage());
        }
    }
}
