package exercise;

import java.util.logging.Logger;

// BEGIN
public class MinThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger("MinLogger");
    private int[] arr;
    private int minNumber = 0;

    public MinThread(int[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        LOGGER.info("INFO " + Thread.currentThread().getName() + " started");
        if (arr != null && arr.length > 0) {
            int min = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] < min) {
                    min = arr[i];
                }
            }
            minNumber = min;
        }
        LOGGER.info("INFO " + Thread.currentThread().getName() + " finished");
    }

    public int getMin() {
        return minNumber;
    }
}
// END
