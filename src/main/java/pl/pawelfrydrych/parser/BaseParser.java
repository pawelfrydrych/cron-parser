package pl.pawelfrydrych.parser;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class BaseParser {
    protected String parseField(String field, int min, int max) {
        if (field.equals("*")) {
            return getAllValuesBetween(min, max);
        } else if ("?".equals(field)) {
            return getDefaultByQuestionMark();
        } else if (field.contains(",")) {
            return getOnlyProvidedValues(field);
        } else if (field.contains("-")) {
            return getValuesInRange(field);
        } else if (field.contains("/")) {
            return getValuesUsingInterval(field, min, max);
        } else {
            return field;
        }
    }

    protected String getDefaultByQuestionMark() {
        return "Question mark is not allowed for this field.";
    }

    protected String getAllValuesBetween(int min, int max) {
        return IntStream.rangeClosed(min, max)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "));
    }

    private String getValuesUsingInterval(String field, int min, int max) {
        String[] values = field.split("/");
        int interval = Integer.parseInt(values[1]);
        return IntStream.rangeClosed(min, max)
                .filter(n -> n % interval == 0)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "));
    }

    protected String getValuesInRange(String field) {
        return getValuesInRange(field.split("-"));
    }

    protected String getValuesInRange(String[] values) {
        int start = Integer.parseInt(values[0]);
        int end = Integer.parseInt(values[1]);
        return getAllValuesBetween(start, end);
    }

    private static String getOnlyProvidedValues(String field) {
        return String.join(" ", field.split(","));
    }

}
