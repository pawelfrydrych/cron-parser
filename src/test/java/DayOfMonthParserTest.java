import org.junit.jupiter.api.Test;
import pl.pawelfrydrych.parser.DayOfMonthParser;
import pl.pawelfrydrych.parser.Parser;
import pl.pawelfrydrych.parser.exceptions.InvalidFormatException;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DayOfMonthParserTest {

    @Test
    void testEveryDayOfMonth() {
        DayOfMonthParser parser = new DayOfMonthParser();
        String result = parser.parse("*");
        String expected = IntStream.rangeClosed(1, 31)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "));
        assertEquals(expected, result);
    }

    @Test
    void testSpecificDayOfMonths() {
        DayOfMonthParser parser = new DayOfMonthParser();
        String result = parser.parse("5,15,25");
        assertEquals("5 15 25", result);
    }

    @Test
    void testRangeOfDayOfMonths() {
        DayOfMonthParser parser = new DayOfMonthParser();
        String result = parser.parse("10-15");
        assertEquals("10 11 12 13 14 15", result);
    }

    @Test
    void testSingleDayOfMonth() {
        DayOfMonthParser parser = new DayOfMonthParser();
        String result = parser.parse("30");
        assertEquals("30", result);
    }

    @Test
    void testInvalidDayOfMonth() {
        assertThrows(InvalidFormatException.class, ()-> Parser.parse("0 0 32 * MON-FRI /usr/bin/find"));
    }
}