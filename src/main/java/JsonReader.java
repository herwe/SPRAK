import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonReader {
    private String word_form;
    private String lemma;
    private String pos_tag;
    private String _case;
    private String definite;
    private String gender;
    private String number;


    public JsonReader(String str, String word) {
        word = word.toLowerCase();
        str = str.toLowerCase();

        str = str.replaceAll("[\\!\\.\\,\\?]", ""); // Strips the String of !?., characters
        JSONParser parser = new JSONParser();
        try (Reader JsonReader = new FileReader("data.json")) {
            JSONObject jsonObject = (JSONObject) parser.parse(JsonReader);

            JSONArray outerArray = (JSONArray) jsonObject.get("sentences");
            JSONArray innerArray = (JSONArray) outerArray.get(0);

            if (!containsWord(str, word)) {
                throw new IllegalStateException("Sentence does not contain word");
            }

            int index = findIndex(str, word);
            setupToString(innerArray, index);

            System.out.println(this.toString());

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private boolean containsWord(String str, String word) {
        String regex = "\\b" + word + "\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    private int findIndex(String str, String word) {
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        for (int i = 1; stringTokenizer.hasMoreTokens(); i++) {
            if (stringTokenizer.nextToken().equals(word)) {
                return i - 1;
            }
        }
        throw new IllegalStateException("This should not happen");
    }

    private void setupToString(JSONArray innerArray, int arrayIndex) {
        JSONObject obj = (JSONObject) innerArray.get(arrayIndex);
        word_form = (String) obj.get("word_form");
        lemma = (String) obj.get("lemma");
        JSONObject ud_tags = (JSONObject) obj.get("ud_tags");
        pos_tag = (String) ud_tags.get("pos_tag");
        JSONObject features = (JSONObject) ud_tags.get("features");
        _case = (String) features.get("Case");
        definite = (String) features.get("Definite");
        gender = (String) features.get("Gender");
        number = (String) features.get("Number");
    }

    @Override
    public String toString() {
        return String.format("""
                 word_form: %s\s
                 lemma: %s\s
                 pos_tag: %s\s
                 case: %s\s
                 definite: %s\s
                 gender: %s\s
                 number: %s""", word_form, lemma, pos_tag, _case, definite, gender, number);
    }
}
