package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.commands.AutoIntakeCargo;
import frc.robot.commands.AutoIntakeHatch;
import frc.robot.commands.AutoScoreCargo;
import frc.robot.commands.AutoScoreHatch;
import frc.robot.commands.ElevatorJoystick;
import frc.robot.commands.ElevatorPreset;
import frc.robot.commands.ElevatorPreset.PresetHeight;
import frc.robot.commands.manipulator.RetractManipulator;
import frc.robot.triggers.ElevatorTrigger;
import frc.robot.triggers.XboxTriggerTrigger;

public class OI {
    static final double joystickDeadzone = 0.25;

    public Trigger elevatorTrigger = new ElevatorTrigger();

    Joystick leftJoystick = new Joystick(0);
    Joystick rightJoystick = new Joystick(1);
    XboxController xboxController = new XboxController(2);

    //Joystick Controls
    Button autoIntakeHatch = new JoystickButton(leftJoystick, 4);
    Button autoIntakeCargo = new JoystickButton(rightJoystick, 5);
    Button autoScoreHatch = new JoystickButton(leftJoystick, 5);
    Button autoScoreCargo = new JoystickButton(rightJoystick, 6);
    
    Button climbMode = new JoystickButton(leftJoystick, 10);

    //Xbox Controls
    Button hatchLevelOne = new JoystickButton(xboxController, 9);

    Button defenseMode = new JoystickButton(xboxController, 10);
    
    Button openHatchMechanism = new JoystickButton(xboxController, 6);
    Button closeHatchMechanism = new JoystickButton(xboxController, 5); //= new XboxTriggerTrigger(xboxController, Hand.kRight); 
    Trigger deployHatchMechanism = new XboxTriggerTrigger(xboxController, Hand.kRight); //= new JoystickButton(xboxController, 5);
    Trigger retractHatchMechanism = new XboxTriggerTrigger(xboxController, Hand.kLeft);

    public void setUpTriggers() {
        elevatorTrigger.whileActive(new ElevatorJoystick());
        //Joystick Controls
        // autoIntakeHatch.whenPressed(new AutoIntakeHatch());
        autoIntakeCargo.whenPressed(new AutoIntakeCargo());
        // autoScoreHatch.whenPressed(new AutoScoreHatch());
        autoScoreCargo.whenPressed(new AutoScoreCargo());

        //Xbox Controls
        hatchLevelOne.whenPressed(new ElevatorPreset(PresetHeight.kHatchLevelOne));
        
        openHatchMechanism.whenPressed(
            new InstantCommand(
                Robot.hatchManipulator,
                () -> Robot.hatchManipulator.setHatchMechanismOpen(true)
            )
        );

        closeHatchMechanism.whenPressed(
            new InstantCommand(
                Robot.hatchManipulator,
                () -> Robot.hatchManipulator.setHatchMechanismOpen(false)
            )
        );
        
        deployHatchMechanism.whenActive(
            new InstantCommand(
                Robot.hatchManipulator,
                () -> Robot.hatchManipulator.setHatchMechanismDeployed(true)
            )
        );
        
        retractHatchMechanism.whenActive(new RetractManipulator());
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
	
	public double getCargoIntakeRollerThrottle() {
        double value = xboxController.getY(Hand.kRight);
        if(Math.abs(value) < 0.3) { return 0; }

        return -value;
    }
    
    public double getElevatorThrottle() {
        return -xboxController.getY(Hand.kLeft);
    }
    
    public double getClimberThrottle() {
        if(xboxController.getYButton()) {
            return 1;
        } else if(xboxController.getBButton()) {
            return -1;
        } else {
            return 0;
        }
    }
    
    public boolean getCancelAuton() {
        return rightJoystick.getTop() && leftJoystick.getTop();
    }

    public boolean getShiftHigh() {
        return rightJoystick.getTrigger();
    }

    public boolean getHalfSpeed() {
        return leftJoystick.getTrigger();
    }
}