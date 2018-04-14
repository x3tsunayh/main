package seedu.address.commons.util;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

//@@author CYX28
public class DateUtilTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getTodayDate_success() {
        LocalDate expectedDate = DateUtil.getTodayDate();
        LocalDate actualDate = LocalDate.now();

        assertEquals(expectedDate, actualDate);
    }

    @Test
    public void getParsedDate_correctFormatDate_success() {
        String dateToParse = "2018-05-12";
        LocalDate expectedParsedDate = LocalDate.parse(dateToParse);
        LocalDate actualParsedDate = DateUtil.getParsedDate(dateToParse);

        assertEquals(expectedParsedDate, actualParsedDate);
    }

    @Test
    public void getParsedDate_wrongFormatDate_fail() {
        thrown.expect(DateTimeParseException.class);
        String dateToParse = "2018 05 12";
        LocalDate expectedParsedDate = LocalDate.parse(dateToParse);
        LocalDate actualParsedDate = DateUtil.getParsedDate(dateToParse);

        assertNotEquals(expectedParsedDate, actualParsedDate);
    }

    @Test
    public void getParsedDateTime_correctFormatDateTime_success() {
        String dateTimeToParse = "2018-05-12 1800";
        LocalDate expectedParsedDateTime = LocalDate.parse(dateTimeToParse.substring(0, 10));
        LocalDate actualParsedDateTime = DateUtil.getParsedDateTime(dateTimeToParse);

        assertEquals(expectedParsedDateTime, actualParsedDateTime);
    }

    @Test
    public void getParsedDateTime_wrongFormatDateTime_fail() {
        thrown.expect(DateTimeParseException.class);
        String dateTimeToParse = "2018 05 12 1800";
        LocalDate expectedParsedDateTime = LocalDate.parse(dateTimeToParse.substring(0, 10));
        LocalDate actualParsedDateTime = DateUtil.getParsedDateTime(dateTimeToParse);

        assertNotEquals(expectedParsedDateTime, actualParsedDateTime);
    }

    @Test
    public void getDayCountBetweenTwoDates_sameYearSameMonthStartDayBeforeEndDay_success() {
        LocalDate startDate = LocalDate.parse("2018-05-10");
        LocalDate endDate = LocalDate.parse("2018-05-12");
        int expectedDayCount = (int) DAYS.between(startDate, endDate);
        int actualDayCount = DateUtil.getDayCountBetweenTwoDates(startDate, endDate);

        assertEquals(expectedDayCount, actualDayCount);
    }

    @Test
    public void getDayCountBetweenTwoDates_sameYearSameMonthSameDay_success() {
        LocalDate startDate = LocalDate.parse("2018-05-12");
        LocalDate endDate = LocalDate.parse("2018-05-12");
        int expectedDayCount = (int) DAYS.between(startDate, endDate);
        int actualDayCount = DateUtil.getDayCountBetweenTwoDates(startDate, endDate);

        assertEquals(expectedDayCount, actualDayCount);
    }

    @Test
    public void getDayCountBetweenTwoDates_sameYearSameMonthStartDayAfterEndDay_success() {
        LocalDate startDate = LocalDate.parse("2018-05-12");
        LocalDate endDate = LocalDate.parse("2018-05-10");
        int expectedDayCount = (int) DAYS.between(startDate, endDate);
        int actualDayCount = DateUtil.getDayCountBetweenTwoDates(startDate, endDate);

        assertEquals(expectedDayCount, actualDayCount);
    }

    @Test
    public void getDayCountBetweenTwoDates_sameYearDifferentMonthSameDay_success() {
        LocalDate startDate = LocalDate.parse("2018-05-12");
        LocalDate endDate = LocalDate.parse("2018-06-12");
        int expectedDayCount = (int) DAYS.between(startDate, endDate);
        int actualDayCount = DateUtil.getDayCountBetweenTwoDates(startDate, endDate);

        assertEquals(expectedDayCount, actualDayCount);
    }

    @Test
    public void getDayCountBetweenTwoDates_sameYearDifferentMonthStartDayBeforeEndDay_success() {
        LocalDate startDate = LocalDate.parse("2018-05-10");
        LocalDate endDate = LocalDate.parse("2018-06-12");
        int expectedDayCount = (int) DAYS.between(startDate, endDate);
        int actualDayCount = DateUtil.getDayCountBetweenTwoDates(startDate, endDate);

        assertEquals(expectedDayCount, actualDayCount);
    }

    @Test
    public void getDayCountBetweenTwoDates_sameYearDifferentMonthStartDayAfterEndDay_success() {
        LocalDate startDate = LocalDate.parse("2018-05-12");
        LocalDate endDate = LocalDate.parse("2018-05-10");
        int expectedDayCount = (int) DAYS.between(startDate, endDate);
        int actualDayCount = DateUtil.getDayCountBetweenTwoDates(startDate, endDate);

        assertEquals(expectedDayCount, actualDayCount);
    }

    @Test
    public void getDayCountBetweenTwoDates_differentYearSameMonthSameDay_success() {
        LocalDate startDate = LocalDate.parse("2017-05-12");
        LocalDate endDate = LocalDate.parse("2018-05-12");
        int expectedDayCount = (int) DAYS.between(startDate, endDate);
        int actualDayCount = DateUtil.getDayCountBetweenTwoDates(startDate, endDate);

        assertEquals(expectedDayCount, actualDayCount);
    }

    @Test
    public void getDayCountBetweenTwoDates_differentYearSameMonthStartDayBeforeEndDay_success() {
        LocalDate startDate = LocalDate.parse("2017-05-10");
        LocalDate endDate = LocalDate.parse("2018-06-12");
        int expectedDayCount = (int) DAYS.between(startDate, endDate);
        int actualDayCount = DateUtil.getDayCountBetweenTwoDates(startDate, endDate);

        assertEquals(expectedDayCount, actualDayCount);
    }

    @Test
    public void getDayCountBetweenTwoDates_differentYearSameMonthStartDayAfterEndDay_success() {
        LocalDate startDate = LocalDate.parse("2017-05-12");
        LocalDate endDate = LocalDate.parse("2018-05-10");
        int expectedDayCount = (int) DAYS.between(startDate, endDate);
        int actualDayCount = DateUtil.getDayCountBetweenTwoDates(startDate, endDate);

        assertEquals(expectedDayCount, actualDayCount);
    }

    @Test
    public void getDayCountBetweenTwoDates_twoDatesWithAndWithoutLeapYear_success() {
        LocalDate startDateWithLeapYear = LocalDate.parse("2012-02-12");
        LocalDate endDateWithLeapYear = LocalDate.parse("2012-03-12");

        int expectedDayCountWithLeapYear = (int) DAYS.between(startDateWithLeapYear, endDateWithLeapYear);
        int actualDayCountWithLeapYear =
                DateUtil.getDayCountBetweenTwoDates(startDateWithLeapYear, endDateWithLeapYear);
        assertEquals(expectedDayCountWithLeapYear, actualDayCountWithLeapYear);

        LocalDate startDateWithoutLeapYear = LocalDate.parse("2017-02-12");
        LocalDate endDateWithoutLeapYear = LocalDate.parse("2017-03-12");

        int expectedDayCountWithoutLeapYear = (int) DAYS.between(startDateWithoutLeapYear, endDateWithoutLeapYear);
        int actualDayCountWithoutLeapYear =
                DateUtil.getDayCountBetweenTwoDates(startDateWithoutLeapYear, endDateWithoutLeapYear);
        assertEquals(expectedDayCountWithoutLeapYear, actualDayCountWithoutLeapYear);

        // compare the day count between difference in dates with and without leap year
        assertNotEquals(actualDayCountWithLeapYear, actualDayCountWithoutLeapYear);

    }

}
