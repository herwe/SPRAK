import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Wordlist {

    private ArrayList<String> words = new ArrayList<>();
    private Map<String, String> sentences = new HashMap<>();

    public Wordlist() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("Sentences.txt"))) {
            String line = bufferedReader.readLine();

            while (line != null) {
                split(line);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void split(String line){
        var splitLine = line.split(",");
        splitLine[1] = splitLine[1].trim();
        sentences.put(splitLine[1], splitLine[0]);
        words.add(splitLine[1]);
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public Map<String, String> getSentences() {
        return sentences;
    }

    public static void main(String[] args) {
        new Wordlist();
    }
}
