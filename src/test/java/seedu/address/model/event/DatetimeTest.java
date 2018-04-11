package seedu.address.model.event;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

//@@author x3tsunayh

public class DatetimeTest {

    @Test
    public void isValidDate() {
        // invalid datetimes
        assertFalse(Datetime.isValidDatetime("")); // empty string
        assertFalse(Datetime.isValidDatetime(" ")); // whitespace only
        assertFalse(Datetime.isValidDatetime("01042018")); // numbers only
        assertFalse(Datetime.isValidDatetime("1st April 2018")); // characters only
        assertFalse(Datetime.isValidDatetime("test123")); // numbers and characters
        assertFalse(Datetime.isValidDatetime("1-09-2018 1845")); // invalid date format
        assertFalse(Datetime.isValidDatetime("29-02-2018 1915")); // invalid day
        assertFalse(Datetime.isValidDatetime("02-13-2018 2030")); // invalid month
        assertFalse(Datetime.isValidDatetime("02-09-18 2030")); // invalid year
        assertFalse(Datetime.isValidDatetime("02-09-2018 2410")); // invalid hour
        assertFalse(Datetime.isValidDatetime("02-09-2018 2060")); // invalid minute

        // valid datetimes
        assertTrue(Datetime.isValidDatetime("01-02-2018 0800"));
        assertTrue(Datetime.isValidDatetime("29-05-2018 1030"));
        assertTrue(Datetime.isValidDatetime("30-10-2018 1345"));
        assertTrue(Datetime.isValidDatetime("31-12-2018 2015"));
    }
}
