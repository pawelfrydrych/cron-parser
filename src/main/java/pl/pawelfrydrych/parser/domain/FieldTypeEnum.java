package pl.pawelfrydrych.parser.domain;

import java.util.List;
import java.util.stream.Collectors;

public enum FieldTypeEnum {
    MINUTE("minute"),
    HOUR("hour"),
    DAY_OF_MONTH("day of month"),
    MONTH("month"),
    DAY_OF_WEEK("day of week");

    FieldTypeEnum(String description) {
        this.description = description;
    }

    private final String description;

    public String getDescription() {
        return description;
    }

    private static final List<FieldTypeEnum> valuesList = List.of(values());

    public static List<FieldTypeEnum> getValuesList() {
        return valuesList;
    }

    public static List<String> getDescriptionList() {
        return valuesList.stream().map(FieldTypeEnum::getDescription).collect(Collectors.toList());
    }
}
