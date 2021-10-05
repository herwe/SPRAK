import java.net.*;
import java.io.*;
import java.io.FileWriter;

public class JsonTagger {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://json-tagger.com/tag");
        String text = "Detta är ett exempel på en mening!";

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.write(text.getBytes());

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;
        try (FileWriter json = new FileWriter("data.json")) {
            while ((output = reader.readLine()) != null) {
                json.write(output);
            }
        }
    }
}
