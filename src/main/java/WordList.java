import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WordList {

    private Map<String, String> sentences = new TreeMap<>();


    public WordList() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("txt/Sentences.txt"))) {
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

    private void split(String line) {
        var splitLine = line.split(",");
        splitLine[1] = splitLine[1].trim();
        sentences.put(splitLine[1], splitLine[0]);
    }

    public Map<String, String> getSentences() {
        return sentences;
    }
}
