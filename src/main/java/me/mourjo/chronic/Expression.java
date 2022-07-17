package me.mourjo.chronic;

import me.mourjo.chronic.field.*;
import me.mourjo.chronic.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses and contains all fields in a cron expression.
 */
final public class Expression {
    final private List<Field> fields;
    final private String rawExpression;
    private boolean isParsed = false;

    public Expression(String cron) {
        rawExpression = cron;
        fields = new ArrayList<>();
    }

    /**
     * Parses the raw expression by tokenizing the expression and calling each subexpression's corresponding Field implementation.
     * <p>
     * Parsing is lazy. Until parsed is called, the raw expression is not consumed. Once parsed, calling parse again is a no-op.
     */
    public void parse() {
        if (!isParsed) {
            List<String> tokens = StringUtils.tokenize(rawExpression);
            fields.add(new MinuteField(tokens.get(0)));
            fields.add(new HourField(tokens.get(1)));
            fields.add(new DayOfMonthField(tokens.get(2)));
            fields.add(new MonthField(tokens.get(3)));
            fields.add(new DayOfWeekField(tokens.get(4)));
            fields.add(new CommandField(tokens.get(5)));

            for (Field f : fields) {
                f.parse();
            }
            isParsed = true;
        }
    }

    @Override
    public String toString() {
        parse();
        StringBuilder s = new StringBuilder();
        for (Field f : fields) {
            s.append(f.describe());
            s.append(": ");
            s.append(f).append("\n");
        }
        return s.toString();
    }
}
