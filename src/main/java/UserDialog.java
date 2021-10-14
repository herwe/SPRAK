import java.util.*;

public class UserDialog {
    private Map<String, String> wordsToTags = new TreeMap<>();
    private Map<String, String> responses = new HashMap<>();
    private Map<String, String> valueResponse = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private JsonWord secretWord;
    private Wordlist wordlist = new Wordlist();
    private Set<String> keywords = new TreeSet<>();

    public UserDialog() {
        wordsToTags.put("ordklass", "pos_tag");
        wordsToTags.put("kasus", "case");
        wordsToTags.put("komparation", "degree");
        wordsToTags.put("numerus", "number");
        wordsToTags.put("bestämdhet", "definite");
        wordsToTags.put("genus", "gender");
        wordsToTags.put("längd", "length");
        wordsToTags.put("första bokstav", "first_letter");
        wordsToTags.put("form", "verbform");
        wordsToTags.put("modus", "mood");
        wordsToTags.put("tempus", "tense");

        keywords.add("ger upp");
        keywords.add("nyckelord");
        keywords.addAll(wordsToTags.keySet());

        responses.put("ordklass","Ordklassen är ");
        responses.put("kasus","Kasuset är ");
        responses.put("komparation","Komparationen är ");
        responses.put("numerus","Numeruset är ");
        responses.put("bestämdhet","Bestämdheten är ");
        responses.put("genus","Genuset är ");
        responses.put("längd","Längden på ordet är ");
        responses.put("första bokstav","Första bokstaven är ");
        responses.put("form","Verbformen är ");
        responses.put("modus","Moduset är ");
        responses.put("tempus","Tempuset är ");

        valueResponse.put("adj","adjektiv");
        valueResponse.put("verb","verb");
        valueResponse.put("noun","substantiv");

        valueResponse.put("plur","plural");
        valueResponse.put("sing","singular");

        valueResponse.put("pos","positiv");

        valueResponse.put("ind","obestämd form");
        valueResponse.put("def","bestämd form");

        //valueResponse.put("ind","indikativt");

        valueResponse.put("past","preteritum");
        valueResponse.put("pres","presens");
        valueResponse.put("fut","futurum");

        valueResponse.put("com","reale");
        valueResponse.put("neut","neutrum");

        valueResponse.put("fin","finit");
        valueResponse.put("inf","infinit");
        valueResponse.put("sup","supinum");
        valueResponse.put("part","particip");



//        for (int i = 0; i < wordlist.getSentences().size(); i++) {
//            for (var pair : selectSecretWord(i).getFeatures().entrySet()) {
//                System.out.println(pair.getKey()+" "+pair.getValue());
//            }
//            System.out.println();
//        }
    }

    public void start() {
        System.out.println("Hej!");
        System.out.println("Du kan fråga vilka nyckelord jag kan genom att fråga mig om nyckelord!");
        System.out.println("Du kan också ge upp genom att skriva \"Jag ger upp\"");
        System.out.println("Låt mig komma på ett ord...");
        secretWord = selectSecretWord();
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
            if (keyword.equalsIgnoreCase("fusk")){
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
                if (valueResponse.containsKey(valueFromWord)){
                    value = valueResponse.get(valueFromWord);
                }
                System.out.println(responses.get(keyword) + value);
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
        if (input.equalsIgnoreCase("fusk")){
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

   /* Wordlist wordlist = new Wordlist();
        for (String targetWord:wordlist.getSentences().keySet()) {
        JsonLoader.start(wordlist.getSentences().get(targetWord), targetWord);
    }*/
}
