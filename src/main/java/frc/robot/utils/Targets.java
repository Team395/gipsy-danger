package frc.robot.utils;

import com._2train395.limelight.api.Target;

public class Targets {
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
