package submission;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @BeforeEach
    public void init(){
        translator = new MorseCodeTranslator();
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testLetterToMorseCode(String input, String expected){
        String actual = translator.translateToMorseCode(input);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testMorseCodeToLetter(String expected, String input){
        String actual = translator.translateToEnglish(input);

        assertEquals(expected, actual);
    }

    @Test
    public void testWordToMorseCode(){
        String expected = "-- --- .-. ... .";

        String actual = translator.translateToMorseCode("MORSE");

        assertEquals(expected, actual);
    }

    @Test
    public void testMorseCodeToWord(){
        String expected = "ENGLISH";

        String actual = translator.translateToEnglish(". -. --. .-.. .. ... ....");

        assertEquals(expected, actual);
    }

    @Test
    public void testMorseCodeToWords(){
        String expected = "HELLOWORLD";

        String actual = translator.translateToEnglish(".... . .-.. .-.. --- .-- --- .-. .-.. -..");

        assertEquals(expected, actual);
    }
}
