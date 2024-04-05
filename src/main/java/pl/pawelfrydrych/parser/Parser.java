package pl.pawelfrydrych.parser;

import pl.pawelfrydrych.parser.domain.FieldTypeEnum;
import pl.pawelfrydrych.parser.exceptions.InvalidFormatException;
import pl.pawelfrydrych.parser.validator.CronValidator;

import java.util.List;

/**
 * Parser class
 */
public class Parser {
    private static final List<String> fieldTypes = FieldTypeEnum.getDescriptionList();
    private static final String COMMAND = "command";

    /**
     * A method that performs the action of parsing the given input into its components
     * for ex. 15 0 1,15 * 1-5 /usr/bin/find
     *
     * @param input Text to parse
     * @throws InvalidFormatException
     */
    public static void parse(String input) throws InvalidFormatException {
        CronValidator.validate(input);
        execute(split(input));
    }

    private static void execute(String[] values) {
        for (int i = 0; i < fieldTypes.size(); i++) {
            parseAndPrint(values, i);
        }

        print(COMMAND, values[5]);
    }

    private static void parseAndPrint(String[] values, int i) {
        String field = fieldTypes.get(i);
        IParser parser = CronFieldParserFactory.getParser(field);
        String result = parser.parse(values[i]);
        print(field, result);
    }

    private static void print(String fieldName, String parsedValues) {
        System.out.printf("%-14s%s\n", fieldName, parsedValues);
    }

    private static String[] split(String value) {
        return value.split(" ");
    }
}
