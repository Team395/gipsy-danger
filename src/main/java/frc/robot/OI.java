package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    Joystick leftJoystick = new Joystick(0);
    Joystick rightJoystick = new Joystick(1);
    XboxController xboxController = new XboxController(2);

    static final double joystickDeadzone = 0.1;
    static final double xboxDeadzone = 0.1;

    private double getJoyY(Joystick stick) {
        if(Math.abs(stick.getY()) < joystickDeadzone) {
            return 0;
        }

        return stick.getY();     
    }

    public double getLeftY() {
        return getJoyY(leftJoystick);
    }

    public double getRightY() {
        return getJoyY(rightJoystick);
    }

}
