# jill858
###### \java\seedu\address\logic\commands\ConvertCommand.java
``` java
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
```
###### \java\seedu\address\logic\commands\FindCommand.java
``` java
    public FindCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute() {
        model.updateFilteredPersonList(predicate);
        return new CommandResult(getMessageForPersonListShownSummary(model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && this.predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
```
###### \java\seedu\address\logic\parser\ConvertCommandParser.java
``` java
/**
 * Parses input arguments and creates a new ConvertCommand object
 */
public class ConvertCommandParser implements Parser<ConvertCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ConvertCommand
     * and returns an ConvertCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ConvertCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        double value;
        String fromCurrencyCode;
        String toCurrencyCode;

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConvertCommand.MESSAGE_USAGE));
        }

        String[] currencyKeywords = trimmedArgs.split("\\s+");

        try {
            if (currencyKeywords.length == 2) {
                //converting base rate
                value = 1;
                fromCurrencyCode = currencyKeywords[0].toUpperCase();
                toCurrencyCode = currencyKeywords[1].toUpperCase();
            } else {
                //converting some value
                value = Double.parseDouble(currencyKeywords[0]);
                fromCurrencyCode = currencyKeywords[1].toUpperCase();
                toCurrencyCode = currencyKeywords[2].toUpperCase();
            }
        } catch (NumberFormatException nfe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConvertCommand.MESSAGE_USAGE));
        } catch (ArrayIndexOutOfBoundsException aie) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConvertCommand.MESSAGE_USAGE));
        }

        boolean isValidFromCurrencyCode = new Currency().containsCurrencyCode(fromCurrencyCode);
        boolean isValidToCurrencyCode = new Currency().containsCurrencyCode(toCurrencyCode);

        if (isValidFromCurrencyCode && isValidToCurrencyCode) {
            return new ConvertCommand(fromCurrencyCode, toCurrencyCode, value);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConvertCommand.MESSAGE_USAGE));
        }

    }
}
```
###### \java\seedu\address\logic\parser\FindCommandParser.java
``` java
        String targetPrefix = getPrefix(trimmedArgs);
        String[] keywords = getKeywords(trimmedArgs);

        if (targetPrefix.equals(PREFIX_NAME.getPrefix())) {
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        } else if (targetPrefix.equals(PREFIX_TAG.getPrefix())) {
            return new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList(keywords)));
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

```
###### \java\seedu\address\logic\parser\FindCommandParser.java
``` java
    /**
     * Extract the search type
     * @param args command line input
     * @return
     */
    private static String getPrefix(String args) {
        return args.substring(0, 2);
    }

```
###### \java\seedu\address\logic\parser\FindCommandParser.java
``` java
    /**
     * Extract keywords out from the command
     * @param args command line input
     * @return
     */
    private static String[] getKeywords(String args) {
        String[] keywords;

        String removePrefixKeywords = args.substring(2);

        return keywords = removePrefixKeywords.split("\\s+");
    }

}
```
###### \java\seedu\address\model\Currency.java
``` java
/**
 * Represent currencies and its rate
 */
public class Currency {

    private List<String> currencyCodes = Arrays.asList("SGD",
            "AUD", "CAD", "CHF", "CNY", "GBP",
            "HKD",  "ILS", "INR", "JPY", "MYR",
            "NZD", "PHP", "SEK", "THB", "TWD",
            "USD");

    /**
     * Check the currency code given contains in the list of currency codes
     * @param currencyCode
     * @return true is a given currency code is found in the list
     */
    public boolean containsCurrencyCode(String currencyCode) {
        boolean isCurrencyCode = currencyCodes.stream().anyMatch(code -> code.equals(currencyCode));

        return isCurrencyCode;
    }

    /**
     * Retrieve the rate of the currency given the currency code
     * The default based rate is in SGD (Singapore Dollar)
     * @param currencyCode
     * @return rate of the currency code
     */
    public double getCurrencyRate(String currencyCode) {
        double currencyRate = 1;

        switch (currencyCode) {

        //Singapore Dollar
        case "SGD":
            return 1.000;

        //Australian dollar
        case "AUD":
            return 0.991;

        //Canadian dollar
        case "CAD":
            return 0.981;

        //Swiss franc
        case "CHF":
            return 0.724;

        //Renminbi (Chinese) yuan
        case "CNY":
            return 4.801;

        //Pound sterling
        case "GBP":
            return 0.539;

        //Hong Kong dollar
        case "HKD":
            return 5.998;

        //Israeli new shekel
        case "ILS":
            return 2.662;

        //Indian rupee
        case "INR":
            return 49.587;

        //Japanese yen
        case "JPY":
            return 80.847;

        //Malaysian ringgit
        case "MYR":
            return 2.962;

        //New Zealand dollar
        case "NZD":
            return 1.049;

        //Philippine piso
        case "PHP":
            return 40.063;

        //Swedish krona/kronor
        case "SEK":
            return 6.279;

        //Thai baht
        case "THB":
            return 23.840;

        //New Taiwan dollar
        case "TWD":
            return 22.285;

        //United States dollar
        case "USD":
            return 0.764;

        default: return 1;
        }
    }
}
```
###### \java\seedu\address\model\tag\TagContainsKeywordsPredicate.java
``` java
/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        String stringifyTags = Joiner.on(" ").join(person.getTags());
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(stringifyTags, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && this.keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
```
