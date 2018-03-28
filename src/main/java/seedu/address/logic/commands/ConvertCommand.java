package seedu.address.logic.commands;

import seedu.address.model.Currency;

/**
 * Converts any amount from one currency to another
 */
public class ConvertCommand extends Command {

    public static final String COMMAND_WORD = "convert";
    public static final String COMMAND_ALIAS = "cv";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Converts an amount from one currency to another\n"
            + "Example: " + COMMAND_WORD + " 1 SGD USD";

    public static final String MESSAGE_COMPLETE = "Converted %s %,.2f to %s %,.2f";

    private Currency currency = new Currency();

    private String fromCurrencyCode;
    private String toCurrencyCode;
    private final double value;

    public ConvertCommand(String fromCurrencyCode, String toCurrencyCode, double value) {
        this.fromCurrencyCode = fromCurrencyCode;
        this.toCurrencyCode = toCurrencyCode;
        this.value = value;
    }

    @Override
    public CommandResult execute() {
        double convertedValue;

        if (fromCurrencyCode.equals("SGD")) {
            //rate is already in the form of base rate
            //therefore multiply to the rate to be converted
            double rate = currency.getCurrencyRate(toCurrencyCode);

            convertedValue = value * rate;
        } else {
            //convert the value to the base rate (SGD)
            //followed by multiplying the rate to be converted
            double fromRate = currency.getCurrencyRate(fromCurrencyCode);
            double toRate = currency.getCurrencyRate(toCurrencyCode);

            double toSgd = value / fromRate;
            convertedValue = toSgd * toRate;
        }

        return new CommandResult(String.format(MESSAGE_COMPLETE,
                fromCurrencyCode, value, toCurrencyCode, convertedValue));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConvertCommand that = (ConvertCommand) o;
        return Double.compare(that.value, value) == 0;
    }
}
