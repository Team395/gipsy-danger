package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.commands.*;
import frc.robot.triggers.*;
import frc.robot.commands.ElevatorPreset.PresetHeight;;

public class OI {
  Joystick leftJoystick = new Joystick(0);
  Joystick rightJoystick = new Joystick(1);
  XboxController xboxController = new XboxController(2);

  Trigger elevatorTrigger;
  Button high = new JoystickButton(xboxController, 4);
  Button medium = new JoystickButton(xboxController, 2);
  Button low = new JoystickButton(xboxController, 1);
  Button stick = new JoystickButton(xboxController, 9);
  
  static final double joystickDeadzone = 0.1;
  static final double xboxDeadzone = 0.1;  

  public void setUpTriggers() {
    elevatorTrigger = new ElevatorTrigger();
    elevatorTrigger.whenActive(new ElevatorJoystick());
    high.whenPressed(new ElevatorPreset(PresetHeight.kMaxHeight));
    medium.whenPressed(new ElevatorPreset(PresetHeight.kCargoMedium));
    low.whenPressed(new ElevatorPreset(PresetHeight.kCargoLow));
    stick.whenPressed(new ElevatorPreset(PresetHeight.kZero));
  }

  private double getJoyY(Joystick stick) {
      if(Math.abs(stick.getY()) < joystickDeadzone) {
          return 0;
      }

      return -stick.getY();     
  }

  public double getLeftY() {
      return getJoyY(leftJoystick);
  }

  public double getRightY() {
      return getJoyY(rightJoystick);
  }

  public double getElevatorThrottle() {
    return -1 * xboxController.getY(Hand.kLeft);
  }
  
  public double getIntakeThrottle() {
      if(Math.abs(xboxController.getY(Hand.kRight)) < xboxDeadzone) {
          return 0;
      }
      return xboxController.getY(Hand.kRight);
  }
  
  public boolean getShiftHigh() {
    return leftJoystick.getTrigger(); 
  }

public boolean getShiftLow() {
    return rightJoystick.getTrigger();
  }
}
