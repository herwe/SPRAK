import java.net.*;
import java.io.*;
import java.io.FileWriter;

public class JsonTagger {
    private BufferedReader reader;

    public JsonTagger() {
        setupTagger();
        createJsonFile();
    }

    public void setupTagger() {
        try {
            URL url = new URL("https://json-tagger.com/tag");
            String text = "Mitt namn är Johan.";
            text = text.toLowerCase();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.write(text.getBytes());

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createJsonFile() {
        try {
            String output;
            try (Writer json = new FileWriter("data.json")) {
                while ((output = reader.readLine()) != null) {
                    json.write(output);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
