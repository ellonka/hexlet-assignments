package exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildAppartmentsList(List<Home> apartments, int n) {
        if (apartments.isEmpty()) {
            return new ArrayList<String>(0);
        }
        List<Home> reorderedApart = apartments.stream().sorted(Home::compareTo).collect(Collectors.toList());
        List<String> result = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            result.add(reorderedApart.get(i).toString());
        }
        return result;
    }
}
// END
