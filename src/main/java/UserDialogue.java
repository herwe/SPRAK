import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UserDialogue {
    private Map<String, String> wordsToTags;
    private Map<String, String> responses;
    private Map<String, String> valueResponse;
    private Scanner scanner = new Scanner(System.in);
    private JsonWord secretWord;
    private WordList wordlist = new WordList();
    private Set<String> keywords = new TreeSet<>();
    private int secretWordNr = 0;

    public UserDialogue() {
        wordsToTags = readFromFile("txt/WordsToTags.txt");
        responses = readFromFile("txt/Responses.txt");
        valueResponse = readFromFile("txt/ValueResponses.txt");

        keywords.add("ger upp");
        keywords.add("nyckelord");
        keywords.addAll(wordsToTags.keySet());
    }

    private Map<String, String> readFromFile(String fileName) {
        Map<String, String> result = new TreeMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] pair = split(line);
                result.put(pair[0], pair[1]);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String[] split(String line) {
        var pair = line.split(",");
        pair[1] = pair[1].trim();
        return pair;
    }

    public void start() {
        System.out.println("Hej!");
        System.out.println("Du kan fråga vilka nyckelord jag kan genom att fråga mig om nyckelord!");
        System.out.println("Du kan också ge upp genom att skriva \"Jag ger upp\"");
        System.out.println("Låt mig komma på ett ord...");

        secretWord = selectSecretWord(secretWordNr);
        secretWordNr++;
        if (secretWordNr>4){
            secretWordNr = 0;
        }

        System.out.println("Nu har jag ett, börja gissa!");
        System.out.println("Exempel: Vilken ordklass är det?");

        dialogLoop();
    }

    private void dialogLoop() {

        do {
            String keyword = handleStringInput();
            if (keyword == null) {
                System.out.println("Det där förstod jag inte riktigt, försök igen.");
                continue;
            }
            if (keyword.equalsIgnoreCase("fusk")) {
                System.out.println(secretWord.toString());
            }
            if (keyword.equalsIgnoreCase(secretWord.getWord_form())) {
                System.out.println("Rätt!");
                break;
            }
            if (keyword.equalsIgnoreCase("ger upp")) {
                System.out.println("Ordet var: " + secretWord.getWord_form());
                break;
            }
            if (keyword.equalsIgnoreCase("nyckelord")) {
                System.out.println("Nyckelorden är: ");
                for (String key : keywords) {
                    System.out.println("\t" + key);
                }
            }
            if (wordsToTags.containsKey(keyword)) {
                String valueFromWord = secretWord.getFeatures().get(wordsToTags.get(keyword));
                if (valueFromWord == null) {
                    System.out.println("Ordet har inte det här egenskapen");
                    continue;
                }
                String value = valueFromWord;
                if (valueResponse.containsKey(valueFromWord)) {
                    value = valueResponse.get(valueFromWord);
                }
                System.out.println(responses.get(keyword) + " " + value);
            }
        } while (true);
    }

    private JsonWord selectSecretWord() {
        return selectSecretWord(new Random().nextInt(wordlist.getSentences().size()));
    }

    private JsonWord selectSecretWord(int n) {
        String wordString = wordlist.getSentences().keySet().toArray()[n].toString();
        return new JsonLoader().start(wordlist.getSentences().get(wordString), wordString);
    }

    private String handleStringInput() {
        System.out.print("> ");
        String input = scanner.nextLine().toLowerCase();
        if (input.contains(secretWord.getWord_form()) || input.contains(secretWord.getLemma())) {
            return secretWord.getWord_form();
        }
        if (input.equalsIgnoreCase("fusk")) {
            return "fusk";
        }
        for (String keyword : keywords) {
            if (input.contains(keyword)) {
                return keyword;
            }
        }
        return null;
    }

    public Map<String, String> getWordsToTags() {
        return wordsToTags;
    }
}
