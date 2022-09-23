package exercise;

class App {
    // BEGIN
    public static String getAbbreviation(String str) {
        String result = "" + Character.toUpperCase(str.charAt(0));

        for (int i = 1; i < str.length(); i++) {
            if (' ' == str.charAt(i - 1)) {
                result += Character.toUpperCase(str.charAt(i));
            }
        }

        return result;
    }
    // END
}
