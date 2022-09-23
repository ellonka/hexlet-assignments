package exercise;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {
    protected String tagName;
    protected Map<String, String> attributes = new LinkedHashMap<>();

    public Tag(String tagName, Map<String, String> attributes) {
        this.tagName = tagName;
        this.attributes.putAll(attributes);
    }

    public String toString() {
        StringBuilder result = new StringBuilder("<" + tagName);
        for (Map.Entry<String, String> pair : attributes.entrySet()) {
            result.append(" " + pair.getKey() + "=\"" + pair.getValue() + "\"");
        }
        result.append(">");
        return result.toString();
    }

}
// END
