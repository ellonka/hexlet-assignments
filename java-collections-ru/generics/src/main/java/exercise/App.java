package exercise;

import java.util.*;

// BEGIN
public class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> element) {
        List<Map<String, String>> result = new LinkedList<>();

        for (Map<String, String> book: books) {  //проход по листу с книгами
            boolean check = true;
            for (String key : element.keySet()) { //проход по мапе элемента
                if (!(element.get(key)).equals(book.get(key))) {
                    check = false;
                }
            }
            if (check) {
                result.add(book);
            }
        }
        return result;
    }
}
//END
