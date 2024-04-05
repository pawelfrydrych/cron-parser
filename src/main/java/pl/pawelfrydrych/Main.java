package pl.pawelfrydrych;

import pl.pawelfrydrych.parser.Parser;
import pl.pawelfrydrych.parser.exceptions.InvalidFormatException;

public class Main {
    public static void main(String[] args) throws InvalidFormatException {
        if (args.length != 1) {
            System.out.println("Error! Missing arguments...");
            return;
        }

        Parser.parse(args[0]);
    }
}
