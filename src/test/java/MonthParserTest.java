import org.junit.jupiter.api.Test;
import pl.pawelfrydrych.parser.MonthParser;
import pl.pawelfrydrych.parser.Parser;
import pl.pawelfrydrych.parser.exceptions.InvalidFormatException;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MonthParserTest {

    @Test
    void testEveryMonth() {
        MonthParser parser = new MonthParser();
        String result = parser.parse("*");
        String expected = IntStream.rangeClosed(1, 12)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "));
        assertEquals(expected, result);
    }

    @Test
    void testSpecificMonths() {
        MonthParser parser = new MonthParser();
        String result = parser.parse("1,2,3");
        assertEquals("1 2 3", result);
    }

    @Test
    void testRangeOfMonths() {
        MonthParser parser = new MonthParser();
        String result = parser.parse("10-11");
        assertEquals("10 11", result);
    }

    @Test
    void testIntervalMonths() {
        MonthParser parser = new MonthParser();
        String result = parser.parse("*/2");
        assertEquals("2 4 6 8 10 12", result);
    }

    @Test
    void testSingleMonth() {
        MonthParser parser = new MonthParser();
        String result = parser.parse("2");
        assertEquals("2", result);
    }

    @Test
    void testInvalidMonth() {
        assertThrows(InvalidFormatException.class, ()-> Parser.parse("0 0 * 13 MON-FRI /usr/bin/find"));
    }
}