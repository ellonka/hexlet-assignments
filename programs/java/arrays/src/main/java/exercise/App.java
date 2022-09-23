package exercise;

import java.util.Arrays;

class App {
    // BEGIN
    public static int[] reverse(int[] numbers) {
        int[] result = new int[numbers.length];
        int k = 0;

        for (int i = numbers.length - 1; i >= 0; i--) {
            result[k] = numbers[i];
            k++;
        }

        return result;
    }

    public static int mult(int[] numbers) {
        int multiplication = 1;

        for (int i = 0; i < numbers.length; i++) {
            multiplication *= numbers[i];
        }

        return multiplication;
    }
    // END
}
