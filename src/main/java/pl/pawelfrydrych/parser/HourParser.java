package pl.pawelfrydrych.parser;

public class HourParser extends BaseParser implements IParser {
    @Override
    public String parse(String field) {
        return parseField(field, 0, 23);
    }
}
