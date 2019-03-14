package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.commands.ApproachTarget;
import frc.robot.enums.TargetType;

public class OI {
  Joystick leftJoystick = new Joystick(0);
  Joystick rightJoystick = new Joystick(1);
  XboxController xboxController = new XboxController(2);

  Trigger elevatorTrigger;
  Button high = new JoystickButton(xboxController, 4);
  Button medium = new JoystickButton(xboxController, 2);
  Button low = new JoystickButton(xboxController, 1);
  Button stick = new JoystickButton(xboxController, 9);
  
  Button climbMode = new JoystickButton(xboxController, 3);

  static final double joystickDeadzone = 0.1;
  static final double xboxDeadzone = 0.25;

  public void setUpTriggers() {
        // elevatorTrigger = new ElevatorTrigger();
        // elevatorTrigger.whenActive(new ElevatorJoystick());
        // high.whenPressed(new ElevatorPreset(PresetHeight.kMaxHeight));
        // medium.whenPressed(new ElevatorPreset(PresetHeight.kCargoMedium));
        // low.whenPressed(new ElevatorPreset(PresetHeight.kCargoLow));
        // stick.whenPressed(new ElevatorPreset(PresetHeight.kZero));
        climbMode.whenPressed(new ApproachTarget(TargetType.kLowTarget));
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
  
  public double getClimberThrottle() {
      if(Math.abs(xboxController.getY(Hand.kRight)) < xboxDeadzone) {
          return 0;
      }
      return -1 * xboxController.getY(Hand.kRight);
  }

  public double getWheelThrottle() {
      double forward = xboxController.getTriggerAxis(Hand.kRight);
      double backward = -1.0 * xboxController.getTriggerAxis(Hand.kLeft);
      if(Math.abs(xboxController.getTriggerAxis(Hand.kRight)) < xboxDeadzone) {
          forward = 0.0;
      }
      if(Math.abs(xboxController.getTriggerAxis(Hand.kLeft)) < xboxDeadzone) {
          backward = 0.0;
      }
      if (forward > 0.0) {
          return forward;
      } else {
          return backward;
      }
  }
  
  public boolean getShiftHigh() {
    return false; //leftJoystick.getTrigger(); 
  }

  public boolean getShiftLow() {
    return false; //rightJoystick.getTrigger();
  }

  public boolean getCancelAuton() {
      return rightJoystick.getTrigger() && leftJoystick.getTrigger();
  }
}
