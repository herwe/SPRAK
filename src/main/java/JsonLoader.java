public class JsonLoader {
    public JsonWord start(String str, String word) {
        new JsonTagger(str);
        return new JsonWord(str, word);
    }
}
