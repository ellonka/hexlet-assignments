// BEGIN
package exercise;

import com.google.gson.Gson;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }

    public static String toJson(String[] conv) {
        Gson gson = new Gson();
        String json = gson.toJson(conv);

        return json;
    }
}
// END
