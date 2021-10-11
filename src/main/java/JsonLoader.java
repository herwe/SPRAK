public class JsonLoader {
    public static void start(String str, String word) {
        new JsonTagger(str);
        new JsonWord(str, word);
    }
}
