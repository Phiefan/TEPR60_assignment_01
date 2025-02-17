package submission;

import java.util.HashMap;

public class MorseCodeTranslator {

    private final HashMap<String, String> alphabet;

    public MorseCodeTranslator() {
        String[] english = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String[] morseCode = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

        this.alphabet = new HashMap<>();

        for (int i = 0; i < 26; i++) {
            this.alphabet.put(english[i], morseCode[i]);
        }
    }

    public String translateToMorseCode(String str) {
        String r = this.alphabet.get(str);
        return r;
    }

    public String translateToEnglish(String str) {
        String r = "";

        for (String k : this.alphabet.keySet()) {
            if (this.alphabet.get(k).equals(str)) {
                r+=k;
            }
        }

        return r;
    }
}
