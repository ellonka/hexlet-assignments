package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] arr) {
        MinThread minThread = new MinThread(arr);
        MaxThread maxThread = new MaxThread(arr);
        minThread.start();
        maxThread.start();
        LOGGER.info("INFO " + Thread.currentThread().getName() + " started");
        Map<String, Integer> result = new HashMap<>();
        try{
            minThread.join();
            maxThread.join();
            LOGGER.info("INFO " + Thread.currentThread().getName() + " finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result.put("min", minThread.getMin());
        result.put("max", maxThread.getMax());
        return result;
    }
    // END
}
