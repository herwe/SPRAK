import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JsonReader {
    private JSONParser parser;

    public JsonReader() {
        parser = new JSONParser();
        try (Reader JsonReader = new FileReader("data.json")) {
            JSONObject jsonObject = (JSONObject) parser.parse(JsonReader);

            JSONArray outerArray = (JSONArray) jsonObject.get("sentences");
            JSONArray innerArray = (JSONArray) outerArray.get(0);

            for (Object tag : innerArray) {
                JSONObject jso = (JSONObject) tag;
                String str = (String) jso.get("word_form");
                System.out.println(str);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
