import java.util.HashMap;
import java.util.Map;

public class UserDialog {
    private Map<String, String> wordsToTags = new HashMap<>();

    public UserDialog() {
        wordsToTags.put("Ordklass", "pos_tag");
        wordsToTags.put("Kasus", "case");
        wordsToTags.put("Komparation", "degree");
        wordsToTags.put("Numerus", "number");
        wordsToTags.put("Bestämdhet", "definite");
        wordsToTags.put("Genus", "gender");
        //wordsToTags.put("", "");
    }

    public Map<String, String> getWordsToTags() {
        return wordsToTags;
    }
}
