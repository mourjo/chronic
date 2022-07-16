package me.mourjo.chronic;

import me.mourjo.chronic.field.*;
import me.mourjo.chronic.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

final public class Expression {
    final private List<Field> fields;
    private boolean isParsed = false;

    Expression(String cron) {
        List<String> tokens = StringUtils.tokenize(cron);
        fields = new ArrayList<>();
        fields.add(new Minute(tokens.get(0)));
        fields.add(new Hour(tokens.get(1)));
        fields.add(new DayOfMonth(tokens.get(2)));
        fields.add(new Month(tokens.get(3)));
        fields.add(new DayOfWeek(tokens.get(4)));
        fields.add(new Command(tokens.get(5)));
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
            s.append(f.toString()).append("\n");
        }
        return s.toString();
    }
}
