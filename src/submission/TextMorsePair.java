package submission;

public class TextMorsePair {
    private final String text;
    private final String morse;

    public TextMorsePair(String text, String morse) {
        this.text = text;
        this.morse = morse;
    }

    public String getText() {
        return this.text;
    }

    public String getMorse() {
        return this.morse;
    }

    public String get(String input) {
        String output = null;

        if (input.equals(this.text)){
            output = this.getMorse();
        } else if (input.equals(this.morse)) {
            output = this.getText();
        }

        return output;
    }
}
