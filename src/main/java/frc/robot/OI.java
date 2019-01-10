package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public final Joystick leftJoystick = new Joystick(RobotMap.LEFT_JOYSTICK);
    public final Joystick rightJoystick = new Joystick(RobotMap.RIGHT_JOYSTICK);

    public double getLeftY() {
        return -leftJoystick.getY();
    }

    public double getRightY() {
        return -rightJoystick.getY();
    }
}
