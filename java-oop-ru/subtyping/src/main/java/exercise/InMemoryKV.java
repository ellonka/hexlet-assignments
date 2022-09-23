package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {
    public Map<String, String> storage = new HashMap<>();

    public InMemoryKV(Map<String, String> storage1) {
        this.storage.putAll(storage1);
    }

    @Override
    public void set(String key, String value) {
        storage.put(key, value);
    }

    @Override
    public void unset(String key) {
        this.storage.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        if (storage.containsKey(key)) {
            return storage.get(key);
        } else {
            return defaultValue;
        }
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> copy = new HashMap<>();
        for (String key: storage.keySet()) {
            copy.put(key, storage.get(key));
        }
        return copy;
    }
}
// END
