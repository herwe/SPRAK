import java.util.Arrays;
import java.util.Scanner;

public class DialogMenu {
    private void menuLoop() {
        boolean exit = false;
        Integer[] allowedInput = {1, 2, 3, 0};
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
     * @return -1 for invalid choice. Returns inputted number if it is allowedInput.
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
        //JsonLoader.start("Jag spelar fotboll men bara kvällstid", "spelar");  // Oracle

        DialogMenu dialogMenu = new DialogMenu();
        dialogMenu.menuLoop();
    }

}
