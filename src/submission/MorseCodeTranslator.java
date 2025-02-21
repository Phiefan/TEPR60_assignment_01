package submission;

public class MorseCodeTranslator {

    private final String[] english;
    private final String[] morseCode;

    public MorseCodeTranslator() {
        this.english = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        this.morseCode = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
    }

    public String translate(String str) {
        String r = "";
        String[] strArray;

        if (str == null || str.isBlank()){
            throw new RuntimeException("Input is null or blank");
        } else if (!this.isEnglish(str) && !this.isMorseCode(str)){
            throw new RuntimeException("Input is untranslatable");
        } else {
            if (this.isMorseCode(str)){
                strArray = str.split(" ");
            } else {
                str = str.toUpperCase();
                str = str.replace(" ","");

                strArray = str.split("");
            }

            for (String s : strArray) {
                for (int i = 0; i < 26; i++) {
                    if (this.english[i].equals(s)){
                        r += this.morseCode[i];
                        r += " ";
                    } else if (this.morseCode[i].equals(s)) {
                        r += this.english[i];
                    }
                }
            }
        }

        return r.trim();
    }

    public boolean isEnglish(String str) {
        return str.matches("[a-zA-Z\\s]*");
    }

    public boolean isMorseCode(String str) {
        return str.matches("[.\\-\\s]*");
    }

}
