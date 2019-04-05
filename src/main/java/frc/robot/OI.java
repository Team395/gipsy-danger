package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.commands.AimAtOffset;
import frc.robot.commands.AutoIntakeCargo;
import frc.robot.commands.AutoIntakeHatch;
import frc.robot.commands.AutoScoreCargo;
import frc.robot.commands.AutoScoreHatch;
import frc.robot.commands.DriveFeet;
import frc.robot.commands.OneShotClimb;
import frc.robot.commands.manipulator.RetractManipulator;
import frc.robot.triggers.ElevatorTrigger;
import frc.robot.triggers.XboxTriggerTrigger;
import frc.robot.enums.TargetType;

public class OI {
    static final double joystickDeadzone = 0.1;

    public Trigger elevatorTrigger = new ElevatorTrigger();

    Joystick leftJoystick = new Joystick(0);
    Joystick rightJoystick = new Joystick(1);
    XboxController xboxController = new XboxController(2);

    //Joystick Controls
    Button autoIntakeHatch = new JoystickButton(leftJoystick, 4);
    Button autoIntakeCargo = new JoystickButton(rightJoystick, 4);
    Button autoScoreHatch = new JoystickButton(leftJoystick, 5);
    Button autoScoreCargo = new JoystickButton(rightJoystick, 5);
    
    Button climbMode = new JoystickButton(leftJoystick, 10);

    //Xbox Controls
    Button cargoLevelTwo = new JoystickButton(xboxController, 4);
    Button cargoShip = new JoystickButton(xboxController, 2);
    Button cargoIntake = new JoystickButton(xboxController, 1);
    Button hatchLevelTwo = new JoystickButton(xboxController, 3);
    Button hatchLevelOne = new JoystickButton(xboxController, 9);

    Button defenseMode = new JoystickButton(xboxController, 10);
    
    Button openHatchMechanism = new JoystickButton(xboxController, 6);
    Button closeHatchMechanism = new JoystickButton(xboxController, 5);
    Trigger deployHatchMechanism = new XboxTriggerTrigger(xboxController, Hand.kRight);
    Trigger retractHatchMechanism = new XboxTriggerTrigger(xboxController, Hand.kLeft);

    public void setUpTriggers() {
        //Joystick Controls
        autoIntakeHatch.whenPressed(new AutoIntakeHatch());
        autoIntakeCargo.whenPressed(new AutoIntakeCargo());
        autoScoreHatch.whenPressed(new AutoScoreHatch()); //TODO: Finish implementing multiple heights
        autoScoreCargo.whenPressed(new AutoScoreCargo());
        
        climbMode.whenPressed(new OneShotClimb());


        //todo: delete
        cargoLevelTwo.whenPressed(new DriveFeet(-1));
        //Xbox Controls
        //cargoLevelTwo.whenPressed(new ElevatorPreset(PresetHeight.kCargoLevelTwo));
        //cargoShip.whenPressed(new ElevatorPreset(PresetHeight.kCargoShip));
        //cargoIntake.whenPressed(new ElevatorPreset(PresetHeight.kCargoIntake));
        //hatchLevelTwo.whenPressed(new ElevatorPreset(PresetHeight.kHatchLevelTwo));
        //hatchLevelOne.whenPressed(new ElevatorPreset(PresetHeight.kHatchLevelOne));

        defenseMode.whenPressed(new RetractManipulator());
        
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
        
        retractHatchMechanism.whenActive(
            new InstantCommand(
                Robot.hatchManipulator,
                () -> Robot.hatchManipulator.setHatchMechanismDeployed(false)
            )
        );
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
        return 0;
    }
    
    public double getWheelThrottle() {
        return 0;
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