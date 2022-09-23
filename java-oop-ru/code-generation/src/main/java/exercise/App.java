package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
    public static void save(Path filePath, Car car) {
        String sequence = car.serialize();
        try {
            Files.writeString(filePath, sequence, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Car extract(Path filePath) throws IOException{
        String content = Files.readString(filePath);
        return Car.unserialize(content);
    }
}
// END
