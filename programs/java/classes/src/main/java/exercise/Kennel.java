package exercise;

import java.util.ArrayList;

// BEGIN
public class Kennel {
    static ArrayList<String[]> puppies = new ArrayList<>();

    public static void addPuppy(String[] puppy) {
        int k = getPuppyCount();
        puppies.add(k, puppy);
    }

    public static void addSomePuppies(String[][] morePuppies) {
        int recentCount = getPuppyCount();
        int k = 0;
        for (int i = recentCount; i < recentCount + morePuppies.length; i++) {
            puppies.add(i, morePuppies[k]);
            k++;
        }
    }

    public static int getPuppyCount() {
        return puppies.size();
    }

    public static boolean isContainPuppy(String name) {
        for (int i = 0; i < getPuppyCount(); i++) {
            if (name.equals(puppies.get(i)[0])) {
                return true;
            }
        }
        return  false;
    }

    public static String[][] getAllPuppies() {
        int count = getPuppyCount();
        String[][] tempPuppies = new String[count][];
        for (int i = 0; i < count; i++) {
            tempPuppies[i] = puppies.get(i);
        }
        return tempPuppies;
    }

    public static String[] getNamesByBreed(String breed) {
        int count = getPuppyCount();
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (breed.equals(puppies.get(i)[1])) {
                names.add(puppies.get(i)[0]);
            }
        }
        String[] namesByBreed = new String[names.size()];
        namesByBreed = names.toArray(namesByBreed);
        return namesByBreed;
    }

    public static void resetKennel() {
        int count = getPuppyCount();
        for (int i = 0; i < count; i++) {
            puppies.remove(0);
        }
    }
}
// END
