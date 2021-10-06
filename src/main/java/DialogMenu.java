import java.util.Arrays;
import java.util.Scanner;

public class DialogMenu {
    private Scanner scanner;

    public DialogMenu() {
        scanner = new Scanner(System.in);
    }

    private void menuLoop() {
        boolean exit = false;
        int[] allowedInput = {1, 2, 3, 0};
        while (!exit) {
            System.out.println("* * * H Ä N G A G U B B E * * *" + "\n" +
                    "1. Spela" + "\n" +
                    "2. Hur spelar man?" + "\n" +
                    "3. Lägg till ord" + "\n" +
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
                    break;
                case 2:
                    //Hur spelar man?
                    break;
                case 3:
                    //Lägg till ord
                    break;
            }
        }
    }

    /**
     * Asks user for input and validates it.
     *
     * @param allowedInput Specifies valid input.
     * @return -1 for invalid choice, 0-3 for valid choices
     */
    private int numericInput(int[] allowedInput) {
        int menuChoice;
        try {
            System.out.print("> ");
            menuChoice = scanner.nextInt();
            if (!Arrays.asList(allowedInput).contains(menuChoice)) {
                throw new Exception();
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
