package fxdata.model.currency;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Currency represents a currency which has a code and a name according to a national currency. Ex. USD (United States Dollar).
 */
public class Currency {
    private String code;
    private String name;

    /**
     * Create a new instance of Currency based on a given code and name.
     * @param code a currency code, must be capital 3 alpha characters.
     * @param name a currency name, must be a non-empty string.
     * @return instance of Currency.
     * @throws CurrencyException if the code or name is invalid.
     */
    public static Currency valueOf(final String code, final String name) {
        validateCode(code);
        validateName(name);

        final Currency c = new Currency();
        c.code = code;
        c.name = name;
        return c;
    }

    /**
     * Get a currency code.
     * @return a currency code.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Get a currency name.
     * @return a currency name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Compare whether a Currency instance is equal to this instance,
     * if they have the same code value, they are equal.
     * @param that an instance to be compared
     * @return true if they are equal.
     */
    @Override
    public boolean equals(final Object that) {
        if (this == that) {
            return true;
        }

        if (!(that instanceof Currency o)) {
            return false;
        }

        return this.code.equals(o.code);
    }

    /**
     * Return a hashcode of an instance, the same code value return the same hashcode.
     * @return a hashcode.
     */
    @Override
    public int hashCode() {
        return this.code.hashCode();
    }

    private static void validateCode(String code) {
        if (Objects.isNull(code)) {
            throw new CurrencyException("Code is null");
        }

//        if (code.isBlank()) {
//            throw new CurrencyException("Code is blank");
//        }

        final String capital3AlphaPattern = "^[A-Z]{3}$";
        final Pattern pattern = Pattern.compile(capital3AlphaPattern);

        if (!pattern.matcher(code).matches()) {
            throw new CurrencyException("Code must be 3 capital alpha characters");
        }
    }

    private static void validateName(String name) {
        if (Objects.isNull(name)) {
            throw new CurrencyException("Name is null");
        }

        final String namePattern = "^[a-zA-Z]+( {0,1}[a-zA-Z])+$";
        final Pattern pattern = Pattern.compile(namePattern);

        if (!pattern.matcher(name).matches()) {
            throw new CurrencyException("Name must be alpha characters each word separated by space");
        }
    }
}
