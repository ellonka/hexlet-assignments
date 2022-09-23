package exercise;

// BEGIN
public class Circle {
    Point point;
    int radius;

    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius >= 0) {
            return Math.PI * radius * radius;
        } else {
            throw new NegativeRadiusException("Radius should be non-negative");
        }
    }
}
// END
