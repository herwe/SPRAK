import java.util.*;

public class UserDialog {
    private Map<String, String> wordsToTags = new TreeMap<>();
    private Scanner scanner = new Scanner(System.in);
    private String secretWord;
    private Wordlist wordlist = new Wordlist();

    public UserDialog() {
        wordsToTags.put("Ordklass", "pos_tag");
        wordsToTags.put("Kasus", "case");
        wordsToTags.put("Komparation", "degree");
        wordsToTags.put("Numerus", "number");
        wordsToTags.put("Bestämdhet", "definite");
        wordsToTags.put("Genus", "gender");
        wordsToTags.put("Längd", "length");
        wordsToTags.put("Första bokstav", "first_letter");

    }

    public void start() {
        System.out.println("Hej! Låt mig komma på ett ord...");
        selectSecretWord();
        System.out.println("Nu har jag ett, börja gissa!");
        System.out.println("Exempel: Vilken ordklass är det?");
    }

    private void selectSecretWord(){
        selectSecretWord(new Random().nextInt(wordlist.getSentences().size()));
    }

    private void selectSecretWord(int n){
        secretWord = wordlist.getSentences().keySet().toArray()[n].toString();
    }

    private String stringInput(){
        return "";
    }

    public Map<String, String> getWordsToTags() {
        return wordsToTags;
    }

   /* Wordlist wordlist = new Wordlist();
        for (String targetWord:wordlist.getSentences().keySet()) {
        JsonLoader.start(wordlist.getSentences().get(targetWord), targetWord);
    }*/
}
