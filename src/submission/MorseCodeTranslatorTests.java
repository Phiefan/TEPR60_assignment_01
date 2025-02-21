package submission;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MorseCodeTranslatorTests {

    private MorseCodeTranslator translator;

    public static Stream<Arguments> testData() {
        return Stream.of(
                arguments("A", ".-"),
                arguments("B", "-..."),
                arguments("C", "-.-."),
                arguments("D", "-.."),
                arguments("E", "."),
                arguments("F", "..-."),
                arguments("G", "--."),
                arguments("H", "...."),
                arguments("I", ".."),
                arguments("J", ".---"),
                arguments("K", "-.-"),
                arguments("L", ".-.."),
                arguments("M", "--"),
                arguments("N", "-."),
                arguments("O", "---"),
                arguments("P", ".--."),
                arguments("Q", "--.-"),
                arguments("R", ".-."),
                arguments("S", "..."),
                arguments("T", "-"),
                arguments("U", "..-"),
                arguments("V", "...-"),
                arguments("W", ".--"),
                arguments("X", "-..-"),
                arguments("Y", "-.--"),
                arguments("Z", "--..")
        );
    }

    public static Stream<Arguments> ignoreCaseData() {
        return Stream.of(
                arguments("only lowercase", "--- -. .-.. -.-- .-.. --- .-- . .-. -.-. .- ... ."),
                arguments("ONE LeTTER", "--- -. . .-.. . - - . .-."),
                arguments("MorE tHan OnE", "-- --- .-. . - .... .- -. --- -. ."),
                arguments("gemener VERSALER", "--. . -- . -. . .-. ...- . .-. ... .- .-.. . .-."),
                arguments("VERSALER gemener", "...- . .-. ... .- .-.. . .-. --. . -- . -. . .-.")
        );
    }

    public static Stream<String> untranslatable() {
        return Stream.of("-9", "13", "0", "927", "å", "Ä", "Ö", "!", "#", "&", "?", "+", "_");
    }

    @BeforeEach
    public void init() {
        translator = new MorseCodeTranslator();
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testLetterToMorseCode(String input, String expected) {
        String actual = translator.translate(input);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testMorseCodeToLetter(String expected, String input) {
        String actual = translator.translate(input);

        assertEquals(expected, actual);
    }

    @Test
    public void testWordToMorseCode() {
        String expected = "-- --- .-. ... .";

        String actual = translator.translate("MORSE");

        assertEquals(expected, actual);
    }

    @Test
    public void testMorseCodeToWord() {
        String expected = "ENGLISH";

        String actual = translator.translate(". -. --. .-.. .. ... ....");

        assertEquals(expected, actual);
    }

    @Test
    public void testMorseCodeToWords() {
        String expected = "HELLOWORLD";

        String actual = translator.translate(".... . .-.. .-.. --- .-- --- .-. .-.. -..");

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"HELLO WORLD", "HELLOWORLD"})
    public void testWordsToMorseCode(String arg) {
        String expected = ".... . .-.. .-.. --- .-- --- .-. .-.. -..";

        String actual = translator.translate(arg);

        assertEquals(expected, actual);
    }

    @Test
    public void testMorseCodeToSentence() {
        String expected = "TRANSLATINGFROMMORSECODETOENGLISH";

        String actual = translator.translate("- .-. .- -. ... .-.. .- - .. -. --. ..-. .-. --- -- -- --- .-. ... . -.-. --- -.. . - --- . -. --. .-.. .. ... ....");

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"TRANSLATING FROM ENGLISH TO MORSE CODE", "TRANSLATINGFROMENGLISHTOMORSECODE"})
    public void testSentenceToMorseCode(String arg) {
        String expected = "- .-. .- -. ... .-.. .- - .. -. --. ..-. .-. --- -- . -. --. .-.. .. ... .... - --- -- --- .-. ... . -.-. --- -.. .";

        String actual = translator.translate(arg);

        assertEquals(expected, actual);
    }

    //  Design choice: Lowercased input shall be handled the same as uppercased input
    @ParameterizedTest
    @CsvSource(value = {"m:--", "i:..", "a:.-", "t:-", "h:....", "o:---"}, delimiter = ':')
    public void testLetterToMorseCodeIgnoreCase(String input, String expected) {
        String actual = translator.translate(input);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("ignoreCaseData")
    public void testTranslateToMorseCodeIgnoreCase(String input, String expected) {
        String actual = translator.translate(input);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"E", "t", "english", "A sentence in English"})
    public void testIsEnglish(String arg) {
        boolean expected = true;

        boolean actual = translator.isEnglish(arg);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {".", "-", "-- --- .-. ... .", "... . -. - . -. -.-. . --- ..-. -- --- .-. ... . -.-. --- -.. ."})
    public void testNotEnglish(String arg) {
        boolean expected = false;

        boolean actual = translator.isEnglish(arg);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {".", "-", "-- --- .-. ... .", "... . -. - . -. -.-. . --- ..-. -- --- .-. ... . -.-. --- -.. ."})
    public void testIsMorseCode(String arg) {
        boolean expected = true;

        boolean actual = translator.isMorseCode(arg);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"E", "t", "english", "A sentence in English"})
    public void testNotMorseCode(String arg) {
        boolean expected = false;

        boolean actual = translator.isMorseCode(arg);

        assertEquals(expected, actual);
    }

    // Design choice: Exception when input is neither English nor Morse Code
    // Implementation fail: can not achieve case where input is both English and Morse Code
    @ParameterizedTest
    @MethodSource("untranslatable")
    @ValueSource(strings = {"Shall.", "- NOT", "-work.", "Hello .-- --- .-. .-.. -..", ".... . .-.. .-.. --- World"})
    public void testExceptionNotEnglishOrMorseCode(String arg) {
        Exception exception = assertThrows(RuntimeException.class, () -> translator.translate(arg));

        String expectedMsg = "Input is untranslatable";
        String actualMsg = exception.getMessage();

        assertEquals(expectedMsg, actualMsg);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n"})
    public void testExceptionNullOrBlank(String arg) {
        Exception exception = assertThrows(RuntimeException.class, () -> translator.translate(arg));

        String expectedMsg = "Input is null or blank";
        String actualMsg = exception.getMessage();

        assertEquals(expectedMsg, actualMsg);
    }

}
