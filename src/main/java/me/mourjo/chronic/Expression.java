package me.mourjo.chronic;

import me.mourjo.chronic.field.*;
import me.mourjo.chronic.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

final public class Expression {
    final private List<Field> fields;
    private boolean isParsed = false;

    public Expression(String cron) {
        List<String> tokens = StringUtils.tokenize(cron);
        fields = new ArrayList<>();
        fields.add(new MinuteField(tokens.get(0)));
        fields.add(new HourField(tokens.get(1)));
        fields.add(new DayOfMonthField(tokens.get(2)));
        fields.add(new MonthField(tokens.get(3)));
        fields.add(new DayOfWeekField(tokens.get(4)));
        fields.add(new CommandField(tokens.get(5)));
    }

    public void parse() {
        if (!isParsed) {
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
