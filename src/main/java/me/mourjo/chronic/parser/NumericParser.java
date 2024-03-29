package me.mourjo.chronic.parser;

import me.mourjo.chronic.exception.UnexpectedAtomException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Parses any numeric cron sub-expression like: hour, minute
 * Checks syntax of a cron string and generates a sequence of numbers according to the given bounds (min/max).
 *
 * Note:
 * - `?` is not supported
 * - Special time strings like `@yearly` are not supported
 * - `,` is given the highest priority, so `/` when applied to a comma-separated string, the `/` applies only to the last item in the list, example: `1-5,11-15/5` will choose 1,2,3,4,5,15
 */
public class NumericParser {
    final int MIN, MAX;
    final Pattern SLASH = Pattern.compile("/");
    final Pattern COMMA = Pattern.compile(",");
    final Pattern DASH = Pattern.compile("-");

    public NumericParser(int min, int max) {
        MIN = min;
        MAX = max;
    }

    public List<Integer> parse(String atom) throws UnexpectedAtomException {
        try {
            validateSyntax(atom);
            return parseAtom(atom).distinct().sorted().toList();
        } catch (NumberFormatException e) {
            throw new UnexpectedAtomException("Invalid Number", e);
        }
    }

    private Stream<Integer> parseAtom(String atom) {
        // operators in order of priority:
        if ("*".equals(atom)) {
            return IntStream.range(MIN, MAX + 1).boxed();
        }

        if (atom.contains(",")) {
            return Arrays.stream(COMMA.split(atom)).flatMap(this::parseAtom).map(this::validateRange);
        }

        if (atom.contains("/")) {
            String[] parts = SLASH.split(atom);
            int div = Integer.parseInt(parts[1]);
            return parseAtom(parts[0]).filter(candidate -> candidate % div == 0).map(this::validateRange);
        }

        if (atom.contains("-")) {
            String[] parts = DASH.split(atom);
            int start = Integer.parseInt(parts[0]);
            int end = Integer.parseInt(parts[1]);
            validateRange(start);
            validateRange(end);
            return IntStream.range(start, end + 1).boxed();
        }

        int value = Integer.parseInt(atom);
        return Stream.of(validateRange(value));
    }

    private int validateRange(int value) {
        if (value < MIN || value > MAX) {
            throw new UnexpectedAtomException("Out of range: " + value);
        }
        return value;
    }

    private void validateSyntax(String atom) {
        int stars = 0, divs = 0;
        for (int i = 0; i < atom.length(); i++) {
            char ch = atom.charAt(i);
            if (isUnexpectedCharacter(ch)) {
                throw new UnexpectedAtomException("Invalid character: " + ch);
            }

            if (ch == '*' && ++stars > 1) {
                throw new UnexpectedAtomException("Only one '*' is allowed");
            }

            if (ch == '/') {
                if (++divs > 1) {
                    throw new UnexpectedAtomException("Only one '/' is allowed");
                }
                if (i == 0 || i == atom.length() - 1) {
                    throw new UnexpectedAtomException("'/' must have a preceding and succeeding value");
                }
                char after = atom.charAt(i + 1);
                if (after < '0' || after > '9') {
                    throw new UnexpectedAtomException("Range invalid, from and two must be integers");
                }
            }

            if (ch == ',') {
                if (i == 0 || i == atom.length() - 1) {
                    throw new UnexpectedAtomException("',' must have a preceding and succeeding value");
                }
            }

            if (ch == '-') {
                if (i == 0 || i == atom.length() - 1) {
                    throw new UnexpectedAtomException("Range must include from and to '-'");
                }
                char before = atom.charAt(i - 1), after = atom.charAt(i + 1);
                if (before < '0' || before > '9' || after < '0' || after > '9') {
                    throw new UnexpectedAtomException("Range invalid, from and two must be integers");
                }
            }
        }
    }

    private boolean isUnexpectedCharacter(char ch) {
        return (ch < '0' || ch > '9') && ch != '-' && ch != '*' && ch != '/' && ch != ',';
    }
}
