package seedu.address.commons.util;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;

//@@author CYX28
/**
 * A container for Date specific utility functions
 */
public class DateUtil {

    /**
     * Get today's date in LocalDate format
     * @return today's date in LocalDate format
     */
    public static LocalDate getTodayDate() {
        return LocalDate.now();
    }

    /**
     * Parse given date into LocalDate format
     * @param dateToParse date in String format
     * @return date in LocalDate format
     */
    public static LocalDate getParsedDate(String dateToParse) {
        return LocalDate.parse(dateToParse);
    }

    /**
     * Get the number of days between the start and end date
     * @param startDate a date in LocalDate format
     * @param endDate a date in LocalDate format
     * @return number of days in between the two dates
     */
    public static int getDayCountBetweenTwoDates(LocalDate startDate, LocalDate endDate) {
        return (int) DAYS.between(startDate, endDate);
    }

}