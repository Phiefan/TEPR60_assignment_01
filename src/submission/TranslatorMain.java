package submission;

import java.util.Scanner;

public class TranslatorMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        MorseCodeTranslator translator = new MorseCodeTranslator();

        try {
            System.out.print("Enter something to translate: ");
            String toTranslate = scan.nextLine();

            String translated = translator.translate(toTranslate);

            System.out.println(toTranslate + " -> " + translated);

        } catch (Exception e) {
            System.out.println("Error! " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }

    }
}
