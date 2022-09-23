package exercise;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
public class App {
    public static String[][] enlargeArrayImage(String[][] image) {
        return Arrays.stream(image)
                .map(vector -> doubleElements(vector))
                .flatMap(vector -> Stream.of(vector, vector))
                .toArray(String[][]::new);
    }
    static String[] doubleElements(String[] vector) {
        return Arrays.stream(vector)
                .flatMap(element -> Stream.of(element, element))
                .toArray(String[]::new);
    }
}
// END
