package frc.robot.utils;

import com._2train395.limelight.api.Target;

public class Targets {
    // All constants are in either inches or radians.
    private static final double TAPE_LENGTH = 5.5;
    private static final double TAPE_WIDTH = 2.0;
    private static final double TAPE_ANGLE = Math.toRadians(14.5);
    private static final double TAPE_SEPARATION = 8.0;
    private static final double TARGET_LENGTH = (2.0 * (TAPE_WIDTH * Math.cos(TAPE_ANGLE))
            + (TAPE_LENGTH * Math.sin(TAPE_ANGLE))) + TAPE_SEPARATION;
    private static final double TARGET_WIDTH = (TAPE_LENGTH * Math.cos(TAPE_ANGLE))
            + (TAPE_WIDTH * Math.sin(TAPE_ANGLE));

    /**
     * @param target
     * @param targetHeight the height in feet of the center of the target from the
     *                     ground
     * @param cameraHeight the height in feet of the center of the camera from the
     *                     ground
     * @param cameraAngle  the angle at which the camera is mounted, in degrees,
     *                     from the horizontal
     * @return the distance in feet to the target, parallel to the ground
     */
    public static double getDistance(final Target target, final double targetHeight, final double cameraHeight,
            double cameraAngle) {
        final double yOffset = Math.toRadians(target.getYOffset());
        cameraAngle = Math.toRadians(cameraAngle);
        return (targetHeight - cameraHeight) / Math.tan(cameraAngle + yOffset);
    }
}
