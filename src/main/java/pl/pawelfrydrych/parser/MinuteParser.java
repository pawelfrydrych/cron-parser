package pl.pawelfrydrych.parser;

public class MinuteParser extends BaseParser implements IParser {
    @Override
    public String parse(String field) {
        return parseField(field, 0, 59);
    }

}
