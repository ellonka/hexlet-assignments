package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
class App {
    public static String getForwardedVariables(String content) {
        String[] lines = content.split("\n");

        return Arrays.stream(lines)
                .filter(line -> line.startsWith("environment"))
                .map(line -> line.replaceAll("environment=\"", ""))
                .flatMap(line -> Arrays.stream(line.split(",")))
                .filter(var -> var.startsWith("X_FORWARDED_"))
                .map(var -> var.replaceAll("X_FORWARDED_", ""))
                .map(var -> var.replaceAll("\"", ""))
                .collect(Collectors.joining(","));
    }
}
//END
