import java.net.*;
import java.io.*;
import java.io.FileWriter;

public class JsonTagger {
    public JsonTagger() throws Exception {
        URL url = new URL("https://json-tagger.com/tag");
        String text = "Mitt namn är Johan.";
        text = text.toLowerCase();

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.write(text.getBytes());

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;
        try (Writer json = new FileWriter("data.json")) {
            while ((output = reader.readLine()) != null) {
                json.write(output);
            }
        }
    }
}
