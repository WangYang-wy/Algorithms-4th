import edu.princeton.cs.algs4.Stack;
import org.jetbrains.annotations.Contract;

import java.util.Arrays;

public class Point2D {

    private final double x;
    private final double y;

    /**
     * constructor.
     *
     * @param x x.
     * @param y y.
     */
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * ccw.
     *
     * @param a Point2D a.
     * @param b Point2D b.
     * @param c Point2D c.
     * @return
     */
    @Contract(pure = true)
    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double area_2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);

        if (area_2 < 0) {
            return -1;
        } else if (area_2 > 0) {
            return 1;
        } else {
            return 0; // collinear.
        }
    }

    /**
     * main test.
     * @param args arguments.
     */
    public static void main(String[] args) {
        Stack<Point2D> hull = new Stack<Point2D>();

//        Arrays.sort();
    }
}
