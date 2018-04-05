package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
//@@author jill858
public class ConvertCommandTest {
    @Test
    public void equals() {
        ConvertCommand convertFirstCommand = new ConvertCommand("SGD", "USD", 0.76);
        ConvertCommand convertSecondCommand = new ConvertCommand("USD", "SGD", 1.31);

        // same object -> returns true
        assertTrue(convertFirstCommand.equals(convertFirstCommand));

        // same values -> returns true
        ConvertCommand convertFirstCommandCopy = new ConvertCommand("SGD", "USD", 0.76);
        assertTrue(convertFirstCommand.equals(convertFirstCommandCopy));

        // different types -> returns false
        assertFalse(convertFirstCommand.equals(1));

        // null -> returns false
        assertFalse(convertFirstCommand.equals(null));

        // different value -> returns false
        assertFalse(convertFirstCommand.equals(convertSecondCommand));
    }

    @Test
    public void execute_convertCurrency_validOutput() {
        double baseRate = 1.00;

        //convert 1 SGD to USD
        String expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "USD", 0.76);
        ConvertCommand command = new ConvertCommand("SGD", "USD", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to AUD
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "AUD", 0.991);
        command = new ConvertCommand("SGD", "AUD", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to CAD
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "CAD", 0.981);
        command = new ConvertCommand("SGD", "CAD", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to CHF
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "CHF", 0.724);
        command = new ConvertCommand("SGD", "CHF", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to CNY
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "CNY", 4.801);
        command = new ConvertCommand("SGD", "CNY", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to GBP
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "GBP", 0.539);
        command = new ConvertCommand("SGD", "GBP", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to HKG
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "HKD", 5.998);
        command = new ConvertCommand("SGD", "HKD", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to ILS
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "ILS", 2.662);
        command = new ConvertCommand("SGD", "ILS", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to INR
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "INR", 49.587);
        command = new ConvertCommand("SGD", "INR", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to JPY
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "JPY", 80.847);
        command = new ConvertCommand("SGD", "JPY", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to MYR
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "MYR", 2.962);
        command = new ConvertCommand("SGD", "MYR", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to NZD
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "NZD", 1.049);
        command = new ConvertCommand("SGD", "NZD", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to PHP
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "PHP", 40.063);
        command = new ConvertCommand("SGD", "PHP", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to SEK
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "SEK", 6.279);
        command = new ConvertCommand("SGD", "SEK", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to THB
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "THB", 23.840);
        command = new ConvertCommand("SGD", "THB", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to TWD
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "TWD", 22.285);
        command = new ConvertCommand("SGD", "TWD", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 10,000 JPY to USD
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "JPY", 10000.00, "USD", 94.5);
        command = new ConvertCommand("JPY", "USD", 10000.00);
        assertCommandResult(command, expectedMessage);
    }

    /**
     * Asserts that the result message from the execution of {@code ConvertCommand} equals to {@code expectedMessage}
     */
    private void assertCommandResult(ConvertCommand convertCommand, String expectedMessage) {
        assertEquals(expectedMessage, convertCommand.execute().feedbackToUser);
    }
}
