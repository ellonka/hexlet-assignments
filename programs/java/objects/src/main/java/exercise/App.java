package exercise;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

class App {
    // BEGIN
    public static String buildList(String[] list) {
        if (list.length > 0) {
            StringBuilder result = new StringBuilder("<ul>\n");

            for (int i = 0; i < list.length; i++) {
                result.append("  <li>");
                result.append(list[i]);
                result.append("</li>\n");
            }
            result.append("</ul>");
            return result.toString();
        } else {
            return "";
        }
    }

    public static String getUsersByYear(String[][] users, int year) {
        String[] names = new String[users.length];
        int k = 0;

        for (int i = 0; i < users.length; i++) {
            LocalDate date = LocalDate.parse(users[i][1]);
            if (date.getYear() == year) {
                names[k] = users[i][0];
                k++;
            }
        }

        String[] namesFin = Arrays.copyOf(names, k);
        return buildList(namesFin);
    }
    // END

    // Это дополнительная задача, которая выполняется по желанию.
    public static String getYoungestUser(String[][] users, String date) throws Exception {
        // BEGIN
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLL yyyy", java.util.Locale.ENGLISH);
        LocalDate parsedDate = LocalDate.parse(date, formatter);

        int k = 0;

        for (int i = 0; i < users.length; i++) {
            LocalDate dateMed = LocalDate.parse(users[i][1]);
            if (dateMed.isBefore(parsedDate)) {
                k++;
            }
        }

        LocalDate[] youngUsersDate = new LocalDate[k];
        int l = 0;

        if (k==0) {
            return "";
        } else {
            for (int i = 0; i < users.length; i++) {
                LocalDate dateMed = LocalDate.parse(users[i][1]);
                if (dateMed.isBefore(parsedDate)) {
                    youngUsersDate[l] = dateMed;
                    l++;
                }
            }
            LocalDate max = youngUsersDate[0];
            for (int i = 1; i < k; i++) {
                if (youngUsersDate[i].isAfter(max)) {
                    max = youngUsersDate[i];
                }
            }
            for (int i = 0; i < users.length; i++) {
                LocalDate dateMed = LocalDate.parse(users[i][1]);
                if (dateMed.isEqual(max)) {
                    return users[i][0];
                }
            }
        }
        return "что-то пошло не так";
        // END
    }
}
