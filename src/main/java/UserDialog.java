import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class UserDialog {
    private Map<String, String> wordsToTags = new TreeMap<>();
    private Scanner scanner = new Scanner(System.in);

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

    }

    public Map<String, String> getWordsToTags() {
        return wordsToTags;
    }
}
