public class JsonLoader {
    public JsonWord start(String sentence, String word) {
        new JsonTagger(sentence);
        return new JsonWord(sentence, word);
    }
}
