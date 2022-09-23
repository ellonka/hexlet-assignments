package exercise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
public class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        return users.stream()
                .filter(user -> "male".equals(user.get("gender")))
                .sorted(new DateComparator())
                .map(user -> user.get("name"))
                .collect(Collectors.toList());
    }

    static LocalDate convertStringToDate(String date) {
        Integer[] dateArr = Arrays.stream(date.split("-"))
                                    .map(elm -> Integer.parseInt(elm))
                                    .toArray(Integer[]::new);
        return LocalDate.of(dateArr[0], dateArr[1], dateArr[2]);
    }
}

class DateComparator implements Comparator<Map<String, String>> {
    public int compare(Map<String, String> user1, Map<String, String> user2) {
        LocalDate date1 = Sorter.convertStringToDate(user1.get("birthday"));
        LocalDate date2 = Sorter.convertStringToDate(user2.get("birthday"));
        return date1.compareTo(date2);
    }
}
// END
