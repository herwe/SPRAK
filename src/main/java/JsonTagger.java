import java.net.*;
import java.io.*;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonTagger {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://json-tagger.com/tag");
        String text = "Detta är ett exempel på en mening!";
        JSONParser parser = new JSONParser();

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

        try (Reader JsonReader = new FileReader("data.json")) {
            JSONObject jsonObject = (JSONObject) parser.parse(JsonReader);

            JSONArray outerArray = (JSONArray) jsonObject.get("sentences");
            JSONArray innerArray = (JSONArray) outerArray.get(0);

            for (Object tag : innerArray) {
                JSONObject jso = (JSONObject) tag;
                String str = (String) jso.get("word_form");
                System.out.println(str);
            }
        }
    }
}
