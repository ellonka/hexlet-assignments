package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        String[] arrWord = sentence.split(" ");
        Map<String, Integer> result = new HashMap<>();

        if (sentence.equals("")) {
            return result;
        }

        for (int i = 0; i < arrWord.length; i++) {
            if (result.containsKey(arrWord[i])) {
                result.put(arrWord[i], result.get(arrWord[i]) + 1);
            } else {
                result.put(arrWord[i], 1);
            }
        }
        return result;
    }

    public static String toString(Map<String, Integer> map) {
        if (map.isEmpty()) {
            return "{}";
        } else {
            String result = "{\n";
            for (String key: map.keySet()) {
                result += "  " + key + ": " + map.get(key) + "\n";
            }
            result += "}";
            return result;
        }
    }
}
//END
