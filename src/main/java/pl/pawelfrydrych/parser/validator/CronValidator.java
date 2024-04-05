package pl.pawelfrydrych.parser.validator;

import org.apache.commons.lang3.math.NumberUtils;
import pl.pawelfrydrych.parser.exceptions.InvalidDayOfWeekException;
import pl.pawelfrydrych.parser.exceptions.InvalidFormatException;

/**
 * Utility class for input validations
 */
public class CronValidator {

    public static void validate(String value) throws InvalidFormatException {
        validateIfIsNullOrEmpty(value);
        String[] vals = value.split(" ");
        validateLength(vals);
        validateRanges(vals);
    }

    public static void validateGreater(int x, int y) throws InvalidDayOfWeekException {
        if (x > y) {
            throw new InvalidDayOfWeekException();
        }
    }

    private static void validateRanges(String[] vals) throws InvalidFormatException {
        validateRange(vals[0], 0, 59);
        validateRange(vals[1], 0, 23);
        validateRange(vals[2], 1, 31);
        validateRange(vals[3], 1, 12);
        validateRange(vals[4], 1, 7);
    }

    private static void validateLength(String[] vals) throws InvalidFormatException {
        if (vals.length != 6) {
            throw new InvalidFormatException();
        }
    }

    private static void validateIfIsNullOrEmpty(String value) throws InvalidFormatException {
        if (value == null || value.isEmpty()) {
            throw new InvalidFormatException();
        }
    }

    private static void validateRange(String value, int min, int max) throws InvalidFormatException {
        if (NumberUtils.isCreatable(value)) {
            int numeric = Integer.parseInt(value);
            if (numeric < min || numeric > max) {
                throw new InvalidFormatException("Invalid cron string format. Value out of the range.");
            }
        }
    }
}
