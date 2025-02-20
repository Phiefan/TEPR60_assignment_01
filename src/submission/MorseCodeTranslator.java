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

    public String translate(String str) {
        String r;

        if (str == null || str.isBlank()){
            throw new RuntimeException("Input is null or blank");
        } else if (!this.isEnglish(str) && !this.isMorseCode(str)){
            throw new RuntimeException("Input is untranslatable");
        } else {
            if (this.isMorseCode(str)){
                r = this.translateToEnglish(str);
            } else {
                r = this.translateToMorseCode(str);
            }
        }

        return r;
    }

    public String translateToMorseCode(String str) {
        String r = "";

        str = str.toUpperCase();
        String[] strArray = str.split("");

        for (String s : strArray) {
            if (!s.isBlank()){
                r += this.alphabet.get(s);
                r += " ";
            }
        }

        return r.trim();
    }

    public String translateToEnglish(String str) {
        String r = "";

        String[] strArray = str.split(" ");

        for (String s : strArray) {
            for (String k : this.alphabet.keySet()) {
                if (s.equals(this.alphabet.get(k))) {
                    r += k;
                }
            }
        }

        return r;
    }

    public boolean isEnglish(String str) {
        return str.matches("[a-zA-Z\\s]*");
    }

    public boolean isMorseCode(String str) {
        return str.matches("[.\\-\\s]*");
    }

}
