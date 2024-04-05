import org.junit.jupiter.api.Test;
import pl.pawelfrydrych.parser.DayOfWeekParser;
import pl.pawelfrydrych.parser.MinuteParser;
import pl.pawelfrydrych.parser.Parser;
import pl.pawelfrydrych.parser.exceptions.InvalidFormatException;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DayOfWeekParserTest {

    @Test
    void testEveryDaysOfWeek() {
        DayOfWeekParser parser = new DayOfWeekParser();
        String result = parser.parse("*");
        String expected = IntStream.rangeClosed(1, 7)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "));
        assertEquals(expected, result);
    }

    @Test
    void testNumericSpecificDayOfWeek() {
        DayOfWeekParser parser = new DayOfWeekParser();
        String result = parser.parse("1");
        assertEquals("1", result);
    }

    @Test
    void testLiteralSpecificDayOfWeek() {
        DayOfWeekParser parser = new DayOfWeekParser();
        String result = parser.parse("MON");
        assertEquals("MON", result);
    }

    @Test
    void testRangeOfDays() {
        DayOfWeekParser parser = new DayOfWeekParser();
        String result = parser.parse("MON-FRI");
        assertEquals("MONDAY TUESDAY WEDNESDAY THURSDAY FRIDAY", result);
    }

    @Test
    void testInvalidDayOfWeek() {
        assertThrows(InvalidFormatException.class, ()-> Parser.parse("120 0 ? * FRI-MON /usr/bin/find"));
    }
}