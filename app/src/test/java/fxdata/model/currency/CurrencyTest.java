package fxdata.model.currency;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {
    @Test
    void valueOf() {
        assertNotNull(Currency.valueOf("USD", "United States Dollar"));
    }

    @Test
    void ofNullCode() {
        Exception exception = assertThrows(CurrencyException.class, () -> Currency.valueOf(null, "United States Dollar"));

        assertEquals("Code is null", exception.getMessage());
    }

//    @ParameterizedTest
//    @ValueSource(strings = {
//        "",         // empty
//        " ",        // single space
//        "  ",       // multiple spaces
//        "\t \n \r", // other whitespaces TAB, NEW LINE
//        "\n",
//        "\t",
//        "\r",
//    })
//    void ofBlankCode(final String code) {
//        Exception exception = assertThrows(CurrencyException.class, () -> Currency.valueOf(code, "United States Dollar"));
//
//        assertEquals("Code is blank", exception.getMessage());
//    }

    @ParameterizedTest
    @ValueSource(strings = {
        "",         // empty
        " ",        // single space
        "  ",       // multiple spaces
        "\t \n \r", // other whitespaces TAB, NEW LINE
        "\n",
        "\t",
        "\r",
        "U",
        "US",
        "US ",
        " SD",
        "US_",
        "_US",
        "uSD",
        "UsD",
        "USd"
    })
    void ofInvalidFormatCode(final String code) {
        Exception exception = assertThrows(CurrencyException.class, () -> Currency.valueOf(code, "United States Dollar"));

        assertEquals("Code must be 3 capital alpha characters", exception.getMessage());
    }

    @Test
    void ofNullName() {
        Exception exception = assertThrows(CurrencyException.class, () -> Currency.valueOf("USD", null));

        assertEquals("Name is null", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "",         // empty
        " ",        // single space
        "  ",       // multiple spaces
        "\t \n \r", // other whitespaces TAB, NEW LINE
        "\n",
        "\t",
        "\r",
        " United States Dollar", // space at the front
        "United States Dollar ", // space at the end
        "United\tStates\tDollar ", // TAB in between
        "\tUnited States Dollar",
        "United States Dollar\n",
        "A  B", // multiple whitespaces in between
    })
    void ofInvalidFormatName(final String name) {
        Exception exception = assertThrows(CurrencyException.class, () -> Currency.valueOf("USD", name));

        assertEquals("Name must be alpha characters each word separated by space", exception.getMessage());
    }

    @Test
    void getCode() {
        final Currency currency = Currency.valueOf("USD", "United States Dollar");

        assertEquals("USD", currency.getCode());
    }

    @Test
    void getName() {
        final Currency currency = Currency.valueOf("USD", "United States Dollar");

        assertEquals("United States Dollar", currency.getName());
    }

    @Test
    void testEqualsSameInstance() {
        final Currency currency1 = Currency.valueOf("USD", "United States Dollar");

        assertEquals(currency1, currency1);
    }

    @Test
    void testEqualsSameCode() {
        final Currency currency1 = Currency.valueOf("USD", "United States Dollar");
        final Currency currency2 = Currency.valueOf("USD", "US Dollar");

        assertEquals(currency1, currency2);
    }

    @Test
    void testEqualsDiffCode() {
        final Currency currency1 = Currency.valueOf("USD", "United States Dollar");
        final Currency currency2 = Currency.valueOf("EUR", "Euro");

        assertNotEquals(currency1, currency2);
    }

    @Test
    void testHashCodeSameInstance() {
        final Currency currency1 = Currency.valueOf("USD", "United States Dollar");

        assertEquals(currency1.hashCode(), currency1.hashCode());
    }

    @Test
    void testHashCodeSameCode() {
        final Currency currency1 = Currency.valueOf("USD", "United States Dollar");
        final Currency currency2 = Currency.valueOf("USD", "US Dollar");

        assertEquals(currency1.hashCode(), currency2.hashCode());
    }

    @Test
    void testHashCodeDiffCode() {
        final Currency currency1 = Currency.valueOf("USD", "United States Dollar");
        final Currency currency2 = Currency.valueOf("EUR", "Euro");

        assertNotEquals(currency1.hashCode(), currency2.hashCode());
    }
}
