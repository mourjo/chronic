package me.mourjo.chronic.util;

import me.mourjo.chronic.exception.IncorrectExpressionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class StringUtils {
    private static final Pattern atomSplitter = Pattern.compile("\\s+");

    public static List<String> tokenize(String expression) {
        var words = Arrays.stream(atomSplitter.split(expression.trim()))
                .map(String::trim)
                .toList();

        if (words.size() < 6) {
            throw new IncorrectExpressionException(expression);
        }

        var tokens = new ArrayList<>(words.subList(0, 5));
        tokens.add(String.join(" ", words.subList(5, words.size())));
        return tokens;
    }
}
