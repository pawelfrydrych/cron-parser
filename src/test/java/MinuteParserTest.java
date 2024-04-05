import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import pl.pawelfrydrych.parser.MinuteParser;
import pl.pawelfrydrych.parser.Parser;
import pl.pawelfrydrych.parser.exceptions.InvalidFormatException;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinuteParserTest {

    @Test
    void testEveryMinute() {
        MinuteParser parser = new MinuteParser();
        String result = parser.parse("*");
        String expected = IntStream.rangeClosed(0, 59)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "));
        assertEquals(expected, result);
    }

    @Test
    void testSpecificMinutes() {
        MinuteParser parser = new MinuteParser();
        String result = parser.parse("5,15,25");
        assertEquals("5 15 25", result);
    }

    @Test
    void testRangeOfMinutes() {
        MinuteParser parser = new MinuteParser();
        String result = parser.parse("10-15");
        assertEquals("10 11 12 13 14 15", result);
    }

    @Test
    void testIntervalMinutes() {
        MinuteParser parser = new MinuteParser();
        String result = parser.parse("*/15");
        assertEquals("0 15 30 45", result);
    }

    @Test
    void testSingleMinute() {
        MinuteParser parser = new MinuteParser();
        String result = parser.parse("30");
        assertEquals("30", result);
    }

    @Test
    void testInvalidMinute() {
        assertThrows(InvalidFormatException.class, ()-> Parser.parse("120 0 ? * MON-FRI /usr/bin/find"));
    }
}