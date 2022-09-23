package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static Long getCountOfFreeEmails(List<String> emails) {
        if (emails.size() == 0) {
            return 0L;
        }
        return emails.stream()
                .filter(email -> email.contains("@gmail.com")
                || email.contains("@yandex.ru")
                || email.contains("@hotmail.com"))
                .collect(Collectors.counting());
    }
}
// END
