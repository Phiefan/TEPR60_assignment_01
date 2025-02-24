package submission;

public class MorseCodeTranslator {

    private final TextMorsePair[] alphabet;

    public MorseCodeTranslator() {
        String[] english = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String[] morseCode = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

        this.alphabet = new TextMorsePair[26];

        for (int i = 0; i < this.alphabet.length; i++) {
            this.alphabet[i] = new TextMorsePair(english[i], morseCode[i]);
        }
    }

    public String translate(String str) {
        String output = "";
        String[] strArray;

        if (str == null || str.isBlank()) {
            throw new RuntimeException("Input is null or blank");
        } else if (!this.isEnglish(str) && !this.isMorseCode(str)) {
            throw new RuntimeException("Input is untranslatable");
        } else {
            if (this.isMorseCode(str)) {
                strArray = str.split(" ");
            } else {
                str = str.toUpperCase();
                strArray = str.split("");
            }

            for (String s : strArray) {
                for (TextMorsePair pair : this.alphabet) {
                    String r = pair.get(s);
                    if (r != null) {
                        output += r;
                        if (this.isEnglish(s)) {
                            output += " ";
                        }
                    }
                }
            }
        }

        return output.trim();
    }

    public boolean isEnglish(String str) {
        return str.matches("[a-zA-Z\\s]+");
    }

    public boolean isMorseCode(String str) {
        return str.matches("[.\\-\\s]+");
    }

}
