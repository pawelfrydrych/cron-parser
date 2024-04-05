package pl.pawelfrydrych.parser;

public class DayOfMonthParser extends BaseParser implements IParser {
    @Override
    public String parse(String field) {
        return parseField(field, 1, 31);
    }

    @Override
    protected String getDefaultByQuestionMark() {
        return getAllValuesBetween(1, 31);
    }
}
