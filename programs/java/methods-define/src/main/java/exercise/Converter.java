package exercise;

class Converter {
    // BEGIN
    public static int convert(int num, String unit) {
        if (unit.equals("Kb")) {
            return num / 1024;
        } else if (unit.equals("b")) {
            return num * 1024;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println("10 Kb = " + convert(10, "b") + " b");
    }
    // END
}
