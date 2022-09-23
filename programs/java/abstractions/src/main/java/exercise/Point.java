package exercise;

class Point {
    // BEGIN
    public static int[] makePoint(int x, int y) {
        int[] point = new int[2];
        point[0] = x;
        point[1] = y;

        return point;
    }

    public static int getX(int[] point) {
        return point[0];
    }

    public static int getY(int[] point) {
        return point[1];
    }

    public static String pointToString(int[] point) {
        return "(" + getX(point) + ", " + getY(point) + ")";
    }

    public static int getQuadrant(int[] point) {
        if (getX(point) > 0 && getY(point) > 0) {
            return 1;
        } else if (getX(point) < 0 && getY(point) > 0) {
            return 2;
        } else if (getX(point) < 0 && getY(point) < 0) {
            return 3;
        } else if (getX(point) > 0 && getY(point) < 0) {
            return 4;
        } else {
            return 0;
        }
    }
    // END
}
