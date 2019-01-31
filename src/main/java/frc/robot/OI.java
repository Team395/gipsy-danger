package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.buttons.Button;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public final Joystick leftJoystick = new Joystick(RobotMap.LEFT_JOYSTICK);
    public final Joystick rightJoystick = new Joystick(RobotMap.RIGHT_JOYSTICK);
    public final XboxController xboxController = new XboxController(2);

    // Button backwardsThreeFeet = new JoystickButton(xboxController, 1);
    // Button right90 = new JoystickButton(xboxController, 2);
    // Button left90 = new JoystickButton(xboxController, 3);
    // Button forwardThreeFeet = new JoystickButton(xboxController, 4);


    public OI() {
        // forwardThreeFeet.whenPressed(new DriveFeet(3,true));
        // right90.whenPressed(new TurnDegrees(-90));
        // left90.whenPressed(new TurnDegrees(90));
        // backwardsThreeFeet.whenPressed(new DriveFeet(-3,true));
    }

    public double getLeftY() {
        return -leftJoystick.getY();
    }

    public double getRightY() {
        return -rightJoystick.getY();
    }

    public boolean getShiftHigh() {
        return leftJoystick.getTrigger(); 
    }

    public boolean getShiftLow() {
        return rightJoystick.getTrigger();
    }
}
