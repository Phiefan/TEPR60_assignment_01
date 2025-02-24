package submission;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextMorsePairTests {
    TextMorsePair pair;

    @BeforeEach
    public void init(){
        pair = new TextMorsePair("T", "-");
    }

    @Test
    public void testGetText(){
        String expected = "T";

        String actual = pair.getText();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetMorse(){
        String expected = "-";

        String actual = pair.getMorse();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource(value = {"T:-", "-:T", "E:", ".:", "3:", "?:", "-7:", "!:"}, delimiter = ':')
    public void testGet(String input, String expected){
        String actual = pair.get(input);

        assertEquals(expected, actual);
    }
}
