package exercise;

import java.util.ArrayList;
import java.util.List;

class SafetyList {
    List<Integer> list = new ArrayList<>();
    // BEGIN
    public void add(int element) {
        synchronized (this) {
            list.add(element);
        }
    }

    public Integer get(int index) {
        return list.get(index);
    }

    public int getSize() {
        return list.size();
    }
    // END
}
