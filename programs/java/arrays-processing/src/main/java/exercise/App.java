package exercise;

import java.util.Arrays;

class App {
    // BEGIN
    public static int getIndexOfMaxNegative(int[] arr) {
        int maxNegative = Integer.MIN_VALUE;
        int index = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0 && arr[i] > maxNegative) {
                maxNegative = arr[i];
                index = i;
            }
        }

        return index;
    }

    public static int[] getElementsLessAverage(int[] arr) {
        int[] transition = new int[arr.length];
        int sum = 0;
        int k = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        double average = (double) sum / arr.length;
        System.out.println("average = " + average);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= average) {
                transition[k] = arr[i];
                k++;
            }
        }

        int[] result = Arrays.copyOf(transition, k);
        return result;
    }
    // END
}
