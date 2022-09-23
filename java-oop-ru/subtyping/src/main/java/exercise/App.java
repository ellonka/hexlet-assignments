package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage object) {
        for (Entry<String, String> pair: object.toMap().entrySet()) {
            object.unset(pair.getKey());
            object.set(pair.getValue(), pair.getKey());
        }
    }

    public static void main(String[] args) {
        KeyValueStorage storage = new InMemoryKV(Map.of("key", "value"));
        storage.set("key2", "value2");
        App.swapKeyValue(storage);
        storage.get("key", "default"); // "default"
        storage.get("value", "default"); // "key"

        System.out.println(storage.toMap());
    }
}
// END
