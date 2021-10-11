import java.util.HashMap;
import java.util.Map;

public class UserDialog {
    private Map<String, String> wordsToTags = new HashMap<>();

    public UserDialog() {
        wordsToTags.put("ordklass", "pos_tag");
        wordsToTags.put("kasus", "case");
        wordsToTags.put("komparation", "degree");
        wordsToTags.put("numerus", "number");
        wordsToTags.put("bestämdhet", "definite");
        wordsToTags.put("genus", "gender");
        //wordsToTags.put("", "");
    }
}
