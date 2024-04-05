package pl.pawelfrydrych.parser.exceptions;

public class InvalidFormatException extends Exception {
    public InvalidFormatException() {
        super("Invalid cron string format. Provided input should contains 5 fields and a command for ex: */15 0 1,15 * 1-5 /usr/bin/find");
    }

    public InvalidFormatException(String msg) {
        super(msg);
    }
}
