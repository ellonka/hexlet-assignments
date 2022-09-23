package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> test = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> expected1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        Assertions.assertIterableEquals(expected1, App.take(test, 3));

        List<Integer> expected2 = new ArrayList<>();
        Assertions.assertIterableEquals(expected2, App.take(test, 0));

        Assertions.assertIterableEquals(test, App.take(test, 8));
        // END
    }
}
