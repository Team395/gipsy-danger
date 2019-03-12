/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils.limelight;

import com._2train395.limelight.api.Target;

/**
 * This class is responsible for calculating the heading offset used by AimAtOffset and DriveToTarget
 */
public class HeadingOffsetCalculator {

    //TODO: Make unified target type with heights baked in

    public enum TargetType {
        kHighTarget(35.962),
        kLowTarget(28.337);
        
        double heightInches;
        
        public double getHeightInches() {
            return heightInches;
        }

        private TargetType(double heightInches) {
            this.heightInches = heightInches;
        }
    }

    static final double cameraHeightInches = 8.375;
	static final double cameraAngle = 30;

    static final double maxOffset = 12.5;
    static final double maxOffsetDistance = 6.0;

    private static double calculateDistance(Contour contour, TargetType targetType) {
        if(contour == null) {
            throw new IllegalArgumentException("Contour passed in was null");
        }    

        return (targetType.getHeightInches() - cameraHeightInches) / 
            Math.tan(Math.toRadians(cameraAngle + contour.yOffset)) / 12;
    }

    private static Side getSide(Corners corners) {
        corners = Limelight.getContourCorners();
        
        if(!corners.validCorners) {
            return null;
        }

        return corners.topRight.y > corners.topLeft.y ? Side.kRight : Side.kLeft;
    }
    
    /**
     * Calculates the distance to turn past cenete to hit the offset point.
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
