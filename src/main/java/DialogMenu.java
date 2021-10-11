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
                    System.out.println("Välkommen till hängagubbe - grammatik" + "\n" +
                            "Istället för att fråga om bokstäver så frågar du om ordegenskaper. " + "\n" +
                            "Ett tips är att börja med att fråga om vilken ordklass det är, för då kan du sedan börja fråga om egenskaperna." + "\n");
                    break;
            }
        }
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
        JsonLoader.start("Nu hoppas jag att allt funkar även med versaler i ordet!", "hoppas");  // Oracle

        DialogMenu dialogMenu = new DialogMenu();
        dialogMenu.menuLoop();
    }

}
