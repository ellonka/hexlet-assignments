package exercise;

class Triangle {
    // BEGIN
    public static double getSquare(int side1, int side2, int angle) {
        final double PI = 3.14;
        double angleRad = angle * PI / 180;

        return 0.5 * side1 * side2 * Math.sin(angleRad);
    }

    public static void main (String[] args) {
        System.out.println(getSquare(4, 5, 45));
    }
    // END
}
