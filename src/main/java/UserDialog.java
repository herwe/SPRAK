import java.util.*;

public class UserDialog {
    private Map<String, String> wordsToTags = new TreeMap<>();
    private Scanner scanner = new Scanner(System.in);
    private JsonWord secretWord;
    private Wordlist wordlist = new Wordlist();

    public UserDialog() {
        wordsToTags.put("ordklass", "pos_tag");
        wordsToTags.put("kasus", "case");
        wordsToTags.put("komparation", "degree");
        wordsToTags.put("numerus", "number");
        wordsToTags.put("bestämdhet", "definite");
        wordsToTags.put("genus", "gender");
        wordsToTags.put("längd", "length");
        wordsToTags.put("första bokstav", "first_letter");

    }

    public void start() {
        System.out.println("Hej! Låt mig komma på ett ord...");
        selectSecretWord();
        System.out.println(secretWord.getWord_form()); //TODO: Remove
        System.out.println("Nu har jag ett, börja gissa!");
        System.out.println("Exempel: Vilken ordklass är det?");
        System.out.println("Du kan också fråga vilka nyckelord jag kan genom att fråga om nyckelord!");
        dialogLoop();
    }

    private void dialogLoop() {
        boolean exit = false;
        do {
            String keyword = handleStringInput();
            if (keyword == null) {
                System.out.println("Det där förstod jag inte riktigt, försök igen.");
                continue;
            }
        } while (!exit);
    }

    private void selectSecretWord() {
        selectSecretWord(new Random().nextInt(wordlist.getSentences().size()));
    }

    private void selectSecretWord(int n) {
        String wordString = wordlist.getSentences().keySet().toArray()[n].toString();
        secretWord = new JsonLoader().start(wordlist.getSentences().get(wordString), wordString);
    }

    private String handleStringInput() {
        String input = scanner.nextLine().toLowerCase();
        String inputtedKeyword = null;
        for (String keyword : wordsToTags.keySet()) {
            if (input.contains(keyword)) {
                inputtedKeyword = keyword;
                break;
            }
        }
        return inputtedKeyword;
    }

    public Map<String, String> getWordsToTags() {
        return wordsToTags;
    }

   /* Wordlist wordlist = new Wordlist();
        for (String targetWord:wordlist.getSentences().keySet()) {
        JsonLoader.start(wordlist.getSentences().get(targetWord), targetWord);
    }*/
}
