// BEGIN
package exercise;

import exercise.geometry.*;

import java.util.Arrays;

public class App {
    public static double[] getMidpointOfSegment(double[][] segment) {
        double[] begin = Segment.getBeginPoint(segment);
        double[] end = Segment.getEndPoint(segment);

        double[] middle = new double[2];
        middle[0] = (Point.getX(begin) + Point.getX(end)) / 2;
        middle[1] = (Point.getY(begin) + Point.getY(end)) / 2;

        return middle;
    }

    public static double[][] reverse(double[][] segment) {
        double[] newBegin = Arrays.copyOf(Segment.getEndPoint(segment), 2);
        double[] newEnd = Arrays.copyOf(Segment.getBeginPoint(segment), 2);

        return Segment.makeSegment(newBegin, newEnd);
    }
}
// END
