package me.mourjo.chronic.atom;

import me.mourjo.chronic.exceptions.UnexpectedAtomException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AtomParser {
    final int MIN, MAX;
    final Pattern SLASH = Pattern.compile("/");
    final Pattern COMMA = Pattern.compile(",");
    final Pattern DASH = Pattern.compile("-");

    AtomParser(int min, int max) {
        MIN = min;
        MAX = max;
    }

    public List<Integer> parse(String atom) throws UnexpectedAtomException {
        try {
            validateSyntax(atom);
            return parseAtom(atom).toList();
        } catch (NumberFormatException e) {
            throw new UnexpectedAtomException("Invalid Number", e);
        }
    }

    private Stream<Integer> parseAtom(String atom) {
        if ("*".equals(atom)) {
            return IntStream.range(MIN, MAX + 1).boxed();
        }

        if (atom.contains("/")) {
            String[] parts = SLASH.split(atom);
            int div = Integer.parseInt(parts[1]);
            return parseAtom(parts[0]).filter(candidate -> candidate % div == 0).map(this::validateRange);
        }

        if (atom.contains(",")) {
            return Arrays.stream(COMMA.split(atom)).flatMap(this::parseAtom).map(this::validateRange);
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

            if ((ch < '0' || ch > '9') && ch != '-' && ch != '*' && ch != '/' && ch != ',') {
                throw new UnexpectedAtomException("Invalid character: " + ch);
            }
        }
    }
}
