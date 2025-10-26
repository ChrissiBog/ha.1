package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Retro calculator")
class CalculatorTest {

    @Test
    @DisplayName("should display result after adding two positive multi-digit numbers")
    void testPositiveAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "40";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display result after getting the square root of two")
    void testSquareRoot() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressUnaryOperationKey("√");

        String expected = "1.41421356";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when dividing by zero")
    void testDivisionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when drawing the square root of a negative number")
    void testSquareRootOfNegative() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressNegativeKey();
        calc.pressUnaryOperationKey("√");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should not allow multiple decimal dots")
    void testMultipleDecimalDots() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(7);
        calc.pressDotKey();
        calc.pressDigitKey(8);

        String expected = "1.78";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }


    //TODO hier weitere Tests erstellen
    
    // Teilaufgabe 1 – Grüner Test
    @Test
    @DisplayName("should display a number with a single decimal dot correctly")
    void shouldAddDecimalDotCorrectly() {
        Calculator calculator = new Calculator();

        calculator.pressDigitKey(1);
        calculator.pressDotKey();
        calculator.pressDigitKey(5);

        assertEquals("1.5", calculator.readScreen());
    }

    // Teilaufgabe 2 - Roter Test 1
    @Test
    @DisplayName("should only clear screen on first press, not memory values")
    void shouldOnlyClearScreenOnFirstPressNotMemory() {
        Calculator calculator = new Calculator();

        // 5 + 3 vorbereiten
        calculator.pressDigitKey(5);
        calculator.pressBinaryOperationKey("+");
        calculator.pressDigitKey(3);

        // Clear einmal drücken → sollte nur Screen löschen, nicht latestValue
        calculator.pressClearKey();

        // Neue Zahl eingeben und Ergebnis berechnen
        calculator.pressDigitKey(2);
        calculator.pressEqualsKey();

        // Erwartet: 5 + 2 = 7
        assertEquals("7", calculator.readScreen());
    }

    // Teilaufgabe 2 - Roter Test 2
    @Test
    @DisplayName("should correctly toggle negative sign before and after operations")
    void shouldToggleNegativeSignCorrectly() {
        Calculator calculator = new Calculator();

        // Erste Zahl eingeben und negativ machen
        calculator.pressDigitKey(5);
        calculator.pressNegativeKey();
        assertEquals("-5", calculator.readScreen());

        // Addition vorbereiten
        calculator.pressBinaryOperationKey("+");
        calculator.pressDigitKey(3);
        calculator.pressEqualsKey();

        // Erwartetes Ergebnis: -5 + 3 = -2
        assertEquals("-2", calculator.readScreen());
    }
}

