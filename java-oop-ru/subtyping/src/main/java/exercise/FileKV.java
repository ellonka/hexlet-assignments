package exercise;

import java.util.Map;

// BEGIN
class FileKV implements KeyValueStorage {
    String folderPath;
    Map<String, String> storage;

    public FileKV(String folderPath, Map<String, String> storage) {
        this.folderPath = folderPath;
        this.storage = storage;
        String jsonFin = Utils.serialize(storage);
        Utils.writeFile(folderPath, jsonFin);
    }

    @Override
    public void set(String key, String value) {
        String json = Utils.readFile(folderPath);
        Map<String, String> tempStorage = Utils.unserialize(json);
        tempStorage.put(key,value);
        String jsonFin = Utils.serialize(tempStorage);
        Utils.writeFile(folderPath, jsonFin);
    }

    @Override
    public void unset(String key) {
        String json = Utils.readFile(folderPath);
        Map<String, String> tempStorage = Utils.unserialize(json);
        tempStorage.remove(key);
        String jsonFin = Utils.serialize(tempStorage);
        Utils.writeFile(folderPath, jsonFin);
    }

    @Override
    public String get(String key, String defaultValue) {
        String json = Utils.readFile(folderPath);
        Map<String, String> tempStorage = Utils.unserialize(json);
        if (tempStorage.containsKey(key)) {
            return tempStorage.get(key);
        } else {
            return defaultValue;
        }
    }

    @Override
    public Map<String, String> toMap() {
        String json = Utils.readFile(folderPath);
        return Utils.unserialize(json);
    }
}
// END
