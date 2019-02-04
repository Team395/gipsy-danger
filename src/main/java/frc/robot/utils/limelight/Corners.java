package frc.robot.utils.limelight;

import java.util.ArrayList;
import java.util.Collections;

import org.opencv.core.Point;

/**
 * Represents the corners of a target contour
 */
public class Corners {

    Point[] corners;

    public Corners(double[] xCorners, double[] yCorners) {
        if((xCorners == null || xCorners.length != 4) || (yCorners == null || yCorners.length != 4)) {
            corners = null;
            return;
        }

        ArrayList<Point> temp = new ArrayList<>();

        for(int i = 0; i < 4; i++) {
            temp.add(i, new Point(xCorners[i], yCorners[i]));
        }

        corners = new Point[]{Collections.max(temp, (Point pt1, Point pt2)->Double.compare(-pt1.x - pt1.y, -pt2.x - pt2.y)),
                                   Collections.max(temp, (Point pt1, Point pt2)->Double.compare(-pt1.x + pt1.y, -pt2.x + pt2.y)),
                                   Collections.max(temp, (Point pt1, Point pt2)->Double.compare( pt1.x + pt1.y,  pt2.x + pt2.y)),
                                   Collections.max(temp, (Point pt1, Point pt2)->Double.compare( pt1.x - pt1.y,  pt2.x - pt2.y))};
    }

    /**
     * @return an array containing corners counterclockwise from the top left
     */
    public Point[] getCorners() {
        return corners;
    }

}
