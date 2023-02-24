package exercise;

import java.util.logging.Logger;

// BEGIN
public class MaxThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger("MaxLogger");
    private int[] arr;
    private int maxNumber = 0;

    public MaxThread(int[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        LOGGER.info("INFO " + Thread.currentThread().getName() + " started");
        if (arr != null && arr.length > 0) {
            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > max) {
                    max = arr[i];
                }
            }
            maxNumber = max;
        }
        LOGGER.info("INFO " + Thread.currentThread().getName() + " finished");
    }

    public int getMax() {
        return maxNumber;
    }
}
// END
