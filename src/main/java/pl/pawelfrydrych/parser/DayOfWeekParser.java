package pl.pawelfrydrych.parser;

import org.apache.commons.lang3.math.NumberUtils;
import pl.pawelfrydrych.parser.exceptions.InvalidDayOfWeekException;
import pl.pawelfrydrych.parser.validator.CronValidator;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public class DayOfWeekParser extends BaseParser implements IParser {

    @Override
    public String parse(String field) {
        return parseField(field, 1, 7);
    }

    @Override
    protected String getDefaultByQuestionMark() {
        return Arrays.stream(DayOfWeek.values())
                .map(val -> val.getDisplayName(TextStyle.FULL, Locale.US))
                .collect(Collectors.joining(" "));
    }

    @Override
    protected String getValuesInRange(String field) {
        String[] values = field.split("-");
        if (NumberUtils.isCreatable(values[0]) && NumberUtils.isCreatable(values[1])) {
            return super.getValuesInRange(values);
        }

        StringBuilder result = new StringBuilder();
        try {
            DayOfWeek firstDay = parseDay(values[0]);
            int lastDayValue = parseDay(values[1]).getValue();
            CronValidator.validateGreater(firstDay.getValue(), lastDayValue);

            for (DayOfWeek day = parseDay(values[0]); day.getValue() <= lastDayValue; day = day.plus(1)) {
                result.append(day);
                if (day.getValue() != lastDayValue) {
                    result.append(" ");
                }
            }
        } catch (InvalidDayOfWeekException ex) {
            ex.printStackTrace();
        }

        return result.toString();
    }

    private DayOfWeek parseDay(String day) throws InvalidDayOfWeekException {
        if (day.startsWith("MON")) {
            return DayOfWeek.MONDAY;
        } else if (day.startsWith("TUE")) {
            return DayOfWeek.TUESDAY;
        } else if (day.startsWith("WED")) {
            return DayOfWeek.WEDNESDAY;
        } else if (day.startsWith("THU")) {
            return DayOfWeek.THURSDAY;
        } else if (day.startsWith("FRI")) {
            return DayOfWeek.FRIDAY;
        } else if (day.startsWith("SAT")) {
            return DayOfWeek.SATURDAY;
        } else if (day.startsWith("SUN")) {
            return DayOfWeek.SUNDAY;
        } else {
            throw new InvalidDayOfWeekException();
        }
    }
}
