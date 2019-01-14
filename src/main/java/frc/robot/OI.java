/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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

    Button backwardsThreeFeet = new JoystickButton(xboxController, 1);
    Button right90 = new JoystickButton(xboxController, 2);
    Button left90 = new JoystickButton(xboxController, 3);
    Button forwardThreeFeet = new JoystickButton(xboxController, 4);


    public OI() {
        forwardThreeFeet.whenPressed(new DriveFeet(3,true));
        right90.whenPressed(new TurnDegrees(-90));
        left90.whenPressed(new TurnDegrees(90));
        backwardsThreeFeet.whenPressed(new DriveFeet(-3,true));
    }

    public double getLeftY() {
        return xboxController.getY(Hand.kLeft);//-leftJoystick.getY();
    }

    public double getRightY() {
        return xboxController.getY(Hand.kRight);//-rightJoystick.getY();
    }
  
    //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
