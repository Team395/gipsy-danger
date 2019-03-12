package frc.robot.utils.limelight;

import java.util.ArrayList;
import java.util.Collections;

import org.opencv.core.Point;

/**
 * Represents the corners of a target contour
 */
public class Corners {

    public final boolean validCorners;
    public final Point topLeft;
    public final Point topRight;
    public final Point bottomLeft;
    public final Point bottomRight;

    public Corners(double[] xCorners, double[] yCorners) {
        if((xCorners == null || xCorners.length != 4) || (yCorners == null || yCorners.length != 4)) {
            validCorners = false;
            topLeft = null;
            topRight = null;
            bottomLeft = null;
            bottomRight = null;
            return;
        }

        validCorners = true;
        ArrayList<Point> temp = new ArrayList<>();

        for(int i = 0; i < 4; i++) {
            temp.add(i, new Point(xCorners[i], yCorners[i]));
        }

        /**
         * This block maximizes certain functions (x+y, x-y, -x+y, -x-y) over the temporary list of points.
         * Each function corresponds to the location in the image the point lies in, i.e. the point with the greatest
         * x+y is going to be the bottom-rightest point in the list. Note, the coordinate system of the limelight is
         * origined in the top-left, with an inverted y-axis.
         */
        
        topLeft     = Collections.max(temp, (Point pt1, Point pt2)->Double.compare(-pt1.x - pt1.y, -pt2.x - pt2.y));
        bottomLeft  = Collections.max(temp, (Point pt1, Point pt2)->Double.compare(-pt1.x + pt1.y, -pt2.x + pt2.y));
        bottomRight = Collections.max(temp, (Point pt1, Point pt2)->Double.compare( pt1.x + pt1.y,  pt2.x + pt2.y));
        topRight    = Collections.max(temp, (Point pt1, Point pt2)->Double.compare( pt1.x - pt1.y,  pt2.x - pt2.y));
    }
}
