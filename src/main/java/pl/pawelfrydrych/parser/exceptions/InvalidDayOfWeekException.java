package pl.pawelfrydrych.parser.exceptions;

public class InvalidDayOfWeekException extends InvalidFormatException {
    public InvalidDayOfWeekException() {
        super("Invalid day format. Provided day of week or his range should be numeric or literal like MON, TUE etc. and properly ordered.");
    }
}
