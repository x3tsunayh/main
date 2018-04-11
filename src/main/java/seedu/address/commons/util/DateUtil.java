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
     * @param dateToParse date must be in String format with hypens e.g. 2018-05-12
     * @return date in LocalDate format
     */
    public static LocalDate getParsedDate(String dateToParse) {
        return LocalDate.parse(dateToParse);
    }

    /**
     * Parse given dateTime into LocalDate format
     * @param dateTimeToParse date must be in String format with hypens e.g. 2018-05-12 1800
     * @return date in LocalDate format
     */
    public static LocalDate getParsedDateTime(String dateTimeToParse) {
        String day = dateTimeToParse.substring(0,2);
        String month = dateTimeToParse.substring(2,6);
        String year = dateTimeToParse.substring(6,10);
        return LocalDate.parse(year + month + day);
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
