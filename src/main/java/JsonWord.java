import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonWord {
    private String word_form;
    private String lemma;
    private String pos_tag;
    private Object[] features_keys = null;
    private Object[] features_values = null;
    private JSONArray innerArray = null;

    public JsonWord(String str, String word) {
        word = word.toLowerCase();
        str = str.toLowerCase();
        str = str.replaceAll("[\\!.\\,\\?]", ""); // Strips the String of !?., characters

        parseJson(str, word);
        int index = findIndex(str, word);
        setupToString(innerArray, index);
        System.out.println(this.toString()); // Temp line (?)
    }

    /**
     * Set ups innerArray to contain all the JSON-information.
     *
     * @param str The given sentence.
     * @param word The given word to be checked for in the sentence.
     */
    private void parseJson(String str, String word) {
        try (Reader JsonReader = new FileReader("data.json")) {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(JsonReader);

            JSONArray outerArray = (JSONArray) jsonObject.get("sentences");
            innerArray = (JSONArray) outerArray.get(0);

            if (!containsWord(str, word)) {
                throw new IllegalStateException("Sentence does not contain word");
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if word is present in the given sentence.
     * Returns true if it is, false if not.
     *
     * @param str The given sentence.
     * @param word The word to be checked for in the sentence.
     * @return results of regular expression
     */
    private boolean containsWord(String str, String word) {
        String regex = "\\b" + word + "\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    /**
     * Checks which index the word in the sentence is located at.
     *
     * @exception IllegalStateException if word is not contained in string
     * @param str The sentence to be tokenized.
     * @param word The word to check for index in tokenized string.
     * @return the index where word is located in the string.
     */
    private int findIndex(String str, String word) {
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        for (int i = 1; stringTokenizer.hasMoreTokens(); i++) {
            if (stringTokenizer.nextToken().equals(word)) {
                return i - 1;
            }
        }
        throw new IllegalStateException("This should not happen");
    }

    /**
     * Assigns instance variable the current states from the given word.
     *
     * @param innerArray array containing information about all the JSON objects.
     * @param arrayIndex index where the current word is located at as an JSON object in the innerArray.
     */
    private void setupToString(JSONArray innerArray, int arrayIndex) {
        JSONObject obj = (JSONObject) innerArray.get(arrayIndex);
        word_form = (String) obj.get("word_form");
        lemma = (String) obj.get("lemma");
        JSONObject ud_tags = (JSONObject) obj.get("ud_tags");
        pos_tag = (String) ud_tags.get("pos_tag").toString().toLowerCase();
        JSONObject features = (JSONObject) ud_tags.get("features");

        setupFeatureObject(features);
    }

    /**
     * Assigns the current words features which is a key and value pair to the instance variables.
     * If the current word does not have any features, assign key as "features" and value as "null".
     *
     * @exception NullPointerException if the word does not have any features.
     * @param features JSONObject containing the features of the current word.
     */
    private void setupFeatureObject(JSONObject features) {
        try {
            features_keys = features.keySet().toArray();
            features_values = features.values().toArray();
        } catch (NullPointerException e) {
            features_keys = new Object[1];
            features_values = new Object[1];
            features_keys[0] = "features";
            features_values[0] = "null";
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("word_form: ").append(word_form).append("\n")
                .append("lemma: ").append(lemma).append("\n")
                .append("pos_tag: ").append(pos_tag).append("\n");

        for (int i = features_keys.length - 1; i >= 0; i--) {
            stringBuilder.append(features_keys[i].toString().toLowerCase())
                    .append(": ").append(features_values[i].toString().toLowerCase()).append("\n");
        }

        return stringBuilder.toString();
    }
}
