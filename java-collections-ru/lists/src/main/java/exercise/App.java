package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

// BEGIN
public class App {
    public static boolean scrabble(String letters, String word) {
        List<Character> arrLetters = transform(letters);
        List<Character> arrWord = transform(word);

        for (Character c: arrWord) {
            if (!arrLetters.remove(c)) {
                return false;
            }
        }
        return true;
    }

    public static List<Character> transform(String str) {
        String newStr = str.toLowerCase();
        List<Character> result = new ArrayList<>();
        for (Character c: newStr.toCharArray()) {
            result.add(c);
        }
        return result;
    }
}
//END
