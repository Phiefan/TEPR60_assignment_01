package submission;

import java.util.Scanner;

public class TranslatorMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        MorseCodeTranslator translator = new MorseCodeTranslator();

        boolean run = true;
        while (run){
            try {
                System.out.print("Enter something to translate: ");
                String input = scan.nextLine();

                if (input.equalsIgnoreCase("stop program")){
                    run = false;
                } else {
                    String output = translator.translate(input);

                    System.out.println(input + " -> " + output);
                }

            } catch (Exception e) {
                System.out.println("Error! " + e.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }

        scan.close();
    }
}
