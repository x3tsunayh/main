package seedu.address.model;

import java.util.Arrays;
import java.util.List;

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
