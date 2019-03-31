package frc.robot.utils.limelight;

import frc.robot.enums.TargetType;
/**
 * This class is responsible for calculating the heading offset used by
 * AimAtOffset and DriveToTarget
*/
public class HeadingOffsetCalculator {
    static final double cameraHeightInches = 8;
    static final double cameraAngle = 30;


    /**
     * maxOffset: The maximum angle offset an approach will target.
     * maxOffsetDistance: The distance at which we will use the maximum offset. Below this distance we scale offset down linearly
     */
    static final double maxOffset = 12.5;
    static final double maxOffsetDistance = 6.0;

    public static Side getSide(Corners corners) {
        corners = Limelight.getContourCorners();
        

        if(!corners.validCorners) {
            return null;
        }

        return corners.topRight.y > corners.topLeft.y ? Side.kRight : Side.kLeft;
    }
    
    public static double calculateDistance(Contour contour, TargetType targetType) {
        if(contour == null) {
            throw new IllegalArgumentException("Contour passed in was null");
        }    

        return (targetType.getHeightInches() - cameraHeightInches) / 
            Math.tan(Math.toRadians(cameraAngle + contour.yOffset)) / 12;
    }

    /**
     * Calculates the distance to turn past center to hit the offset point.
     * A positive angle denotes turning in a positive direction.
     */
    public static double calculateAdditionalOffset(Contour contour, Corners corners, TargetType targetType) {
        if(contour == null) {
            throw new IllegalArgumentException("Contour passed in was null");
        }    

        double distance = calculateDistance(contour, targetType);
        Side side = getSide(corners);

        double additionalOffsetSign = side == Side.kLeft ? 1 : -1;
        double distanceScaling = Math.min(1, (distance * distance) / (maxOffsetDistance * maxOffsetDistance));
        
        return maxOffset * distanceScaling * additionalOffsetSign;    
    }

    /**
     * Calculates the distance to turn to hit the offset point.
     * A positive angle denotes turning in a positive direction.
     */
    public static double calculateTotalOffset(Contour contour, Corners corners, TargetType targetType) {
        return -contour.xOffset + calculateAdditionalOffset(contour, corners, targetType);
    }

    private HeadingOffsetCalculator() {

    }

    private enum Side {
        kLeft,
        kRight
    };
}
