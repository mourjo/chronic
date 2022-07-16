package me.mourjo.chronic.utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringUtils {
    private static final Pattern atomSplitter = Pattern.compile("\\s+");

    public static List<String> tokenize(String expression) {
        // currently, does not support command args
        return Arrays.stream(atomSplitter.split(expression.trim(), 8))
                .map(String::trim).collect(Collectors.toList());
    }
}
