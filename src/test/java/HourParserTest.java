import org.junit.jupiter.api.Test;
import pl.pawelfrydrych.parser.HourParser;
import pl.pawelfrydrych.parser.Parser;
import pl.pawelfrydrych.parser.exceptions.InvalidFormatException;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HourParserTest {

    @Test
    void testEveryHour() {
        HourParser parser = new HourParser();
        String result = parser.parse("*");
        String expected = IntStream.rangeClosed(0, 23)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "));
        assertEquals(expected, result);
    }

    @Test
    void testSpecificHours() {
        HourParser parser = new HourParser();
        String result = parser.parse("5,15,23");
        assertEquals("5 15 23", result);
    }

    @Test
    void testRangeOfHours() {
        HourParser parser = new HourParser();
        String result = parser.parse("10-15");
        assertEquals("10 11 12 13 14 15", result);
    }

    @Test
    void testIntervalHours() {
        HourParser parser = new HourParser();
        String result = parser.parse("*/2");
        assertEquals("0 2 4 6 8 10 12 14 16 18 20 22", result);
    }

    @Test
    void testSingleHour() {
        HourParser parser = new HourParser();
        String result = parser.parse("30");
        assertEquals("30", result);
    }

    @Test
    void testInvalidHour() {
        assertThrows(InvalidFormatException.class, () -> Parser.parse("0 25 ? * MON-FRI /usr/bin/find"));
    }
}