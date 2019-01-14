package frc.robot.commands;

import com._2train395.limelight.api.Limelight;
import com._2train395.limelight.api.Target;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.utils.Targets;
import frc.robot.utils.Targets.TargetType;

public class ApproachTarget extends CommandGroup {
    /**
     * The distance, in inches, to arrive at from the target.
     */
    private static final double DESIRED_DISTANCE_FROM_TARGET = 24.0;
    /**
     * The height in inches of the center of the camera from the ground.
     */
    private static final double CAMERA_HEIGHT = 0.0; // TODO: Measure this value
    /**
     * The angle at which the camera is mounted, in degrees, from the horizontal.
     */
    private static final double CAMERA_ANGLE = 0.0; // TODO: Measure this value

    public ApproachTarget(final TargetType targetType, final ApproachingDirection approachingDirection) {
        final Target target = Limelight.getTarget();
        final double distance = Targets.getDistance(target, targetType, CAMERA_HEIGHT, CAMERA_ANGLE);
        final double xOffset = Math.toRadians(target.getXOffset());

        // The skew from Limelight is the same sign no matter which way the target
        // appears tilted. Therefore, we need to adjust the sign manually depending on
        // from where the robot is approaching.
        double skew = 0.0;
        switch (approachingDirection) {
            case LEFT:
                skew = Math.toRadians(-target.getSkew());
            case RIGHT:
                skew = Math.toRadians(target.getSkew());
        }

        final double distanceToPos = Math
                .sqrt((distance * distance) + (DESIRED_DISTANCE_FROM_TARGET * DESIRED_DISTANCE_FROM_TARGET)
                        - (2.0 * distance * DESIRED_DISTANCE_FROM_TARGET * Math.cos(skew))); // Law of Cosines

        final double angleBetweenPosAndTarget = DESIRED_DISTANCE_FROM_TARGET * skew / distanceToPos; // Law of Sines
        final double angleToPos = xOffset - angleBetweenPosAndTarget;
        final double angleFromPosToTarget = angleBetweenPosAndTarget + skew;

        addSequential(new TurnDegrees(Math.toDegrees(angleToPos)));
        addSequential(new DriveFeet(distanceToPos / 12.0));
        addSequential(new TurnDegrees(Math.toDegrees(angleFromPosToTarget)));
    }

    /**
     * Represents where, relative to the target, the robot is approaching from.
     */
    public enum ApproachingDirection {
        LEFT, RIGHT
    }
}
