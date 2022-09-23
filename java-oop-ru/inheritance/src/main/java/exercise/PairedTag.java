package exercise;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

// BEGIN
public class PairedTag extends Tag {
    String tagBody;
    List<Tag> children = new ArrayList<>();

    public PairedTag(String tagName, Map<String, String> attributes, String tagBody, List<Tag> children) {
        super(tagName, attributes);
        this.tagBody = tagBody;
        this.children.addAll(children);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());
        for (Tag child: children) {
            result.append(child.toString());
        }
        result.append(tagBody);
        result.append("</" + tagName + ">");
        return result.toString();
    }
}
// END
