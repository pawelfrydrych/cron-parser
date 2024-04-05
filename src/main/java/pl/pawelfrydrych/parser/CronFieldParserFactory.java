package pl.pawelfrydrych.parser;

public class CronFieldParserFactory {
    public static IParser getParser(String fieldType) {
        return switch (fieldType) {
            case "minute" -> new MinuteParser();
            case "hour" -> new HourParser();
            case "day of month" -> new DayOfMonthParser();
            case "day of week" -> new DayOfWeekParser();
            case "month" -> new MonthParser();
            default -> throw new IllegalArgumentException("Invalid field type: " + fieldType);
        };
    }
}
