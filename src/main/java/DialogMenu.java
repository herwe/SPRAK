import java.util.Arrays;
import java.util.Scanner;

public class DialogMenu {
    UserDialog userDialog = new UserDialog();

    private void menuLoop() {
        boolean exit = false;
        Integer[] allowedInput = {1, 2, 3, 0};
        while (!exit) {
            System.out.println("* * * H Ä N G A G U B B E * * *" + "\n" +
                    "1. Spela" + "\n" +
                    "2. Hur spelar man?" + "\n" +
                    "0. Avsluta");
            switch (numericInput(allowedInput)) {
                case -1:
                    System.out.println("Ogiltigt alternativ, försök igen.");
                    break;
                case 0:
                    //Avsluta
                    exit = true;
                    break;
                case 1:
                    //Spela
                    userDialog.start();
                    break;
                case 2:
                    //Hur spelar man?
                    printHowTo();
                    break;
            }
        }
    }

    private void printHowTo() {
        System.out.println("Välkommen till hängagubbe - grammatik" + "\n" +
                "Istället för att fråga om bokstäver så frågar du om ordegenskaper. " + "\n" +
                "Botten väljer ett ord ur en mening och ditt uppdrag är att gissa vilket ord det är." + "\n" +
                "Du får rätt om du lyckas gissa ordet eller dess lemma." + "\n" +
                "Här är de nyckelord du kan fråga om: ");
        for (String keyword : userDialog.getWordsToTags().keySet()) {
            System.out.println("\t" + keyword);
        }
        System.out.println();
    }

    /**
     * Asks user for input and validates it.
     *
     * @param allowedInput Specifies valid input.
     * @return -1 for invalid choice. Returns inputted number if it is in allowedInput.
     */
    private int numericInput(Integer[] allowedInput) {
        Scanner scanner = new Scanner(System.in);
        int menuChoice;
        try {
            System.out.print("> ");
            menuChoice = scanner.nextInt();
            if (!Arrays.asList(allowedInput).contains(menuChoice)) {
                throw new Exception("Input is not in allowedInput");
            }
        } catch (Exception e) {
            return -1;
        }
        return menuChoice;
    }

    public static void main(String[] args) {
        DialogMenu dialogMenu = new DialogMenu();
        dialogMenu.menuLoop();
    }

}
