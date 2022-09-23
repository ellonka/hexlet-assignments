package exercise;

import java.util.*;

// BEGIN
class App {
    public static Map<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, String> result = new TreeMap<>();

        for (String key: map1.keySet()) {
            if (map1.get(key).equals(map2.get(key))) {
                result.put(key, "unchanged");
            } else {
                result.put(key, "changed");
            }
            if (map2.remove(key) == null) {
                result.put(key, "deleted");
            }
        }
        for (String key: map2.keySet()) {
            result.put(key, "added");
        }

        return result;
    }
}
//END
