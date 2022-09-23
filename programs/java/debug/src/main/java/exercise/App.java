package exercise;

class App {
    // BEGIN
    public static String getTypeOfTriangle(int side1, int side2, int side3) {
        if (side1 + side2 > side3 && side2 + side3 > side1 && side1 + side3 > side2) {
            if (side1 == side2 && side2 == side3) {
                return "Равносторонний";
            } else if (side1 == side2 || side2 == side3 || side1 == side3) {
                return "Равнобедренный";
            } else {
                return "Разносторонний";
            }
        } else {
            return "Треугольник не существует";
        }
    }
    // END
}
