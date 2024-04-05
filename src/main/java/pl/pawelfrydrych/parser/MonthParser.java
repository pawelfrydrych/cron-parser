package pl.pawelfrydrych.parser;

public class MonthParser extends BaseParser implements IParser {
    @Override
    public String parse(String field) {
        return parseField(field, 1, 12);
    }
}
