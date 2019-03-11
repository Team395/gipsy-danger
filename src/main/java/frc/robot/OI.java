package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.commands.ConditionalAutoIntake;
import frc.robot.commands.ElevatorPreset;
import frc.robot.commands.ElevatorPreset.PresetHeight;
import frc.robot.utils.limelight.Limelight;
import frc.robot.utils.limelight.Limelight.Pipeline;

public class OI {
	Joystick leftJoystick = new Joystick(0);
	Joystick rightJoystick = new Joystick(1);
	ControlBoard controlBoard = new ControlBoard(2);
	
	static final double joystickDeadzone = 0.1;

	Button elevatorHigh   = new JoystickButton(controlBoard, 1);
	Button elevatorMedium = new JoystickButton(controlBoard, 2);
	Button elevatorLow    = new JoystickButton(controlBoard, 3);
	Button elevatorIntake = new JoystickButton(controlBoard, 4);
	Button elevatorGround = new JoystickButton(controlBoard, 5);
	Button autoIntake     = new JoystickButton(controlBoard, 6);
	Button autoScore      = new JoystickButton(controlBoard, 7);
	Button disableVacuum  = new JoystickButton(controlBoard, 8);
	Button enableVacuum   = new JoystickButton(controlBoard, 9);
	Button retractFourBar = new JoystickButton(controlBoard, 10);
	Button extendFourBar  = new JoystickButton(controlBoard, 11);
	Button fineAdjustUp   = new JoystickButton(controlBoard, 12);
	Button fineAdjustDown = new JoystickButton(controlBoard, 13);
	Button spinIntakeOut  = new JoystickButton(controlBoard, 14);
	Button spinIntakeIn   = new JoystickButton(controlBoard, 15);
	Button leftTarget     = new JoystickButton(controlBoard, 16); //If pressed, left target, otherwise right target
	Button enableClimber  = new JoystickButton(controlBoard, 17);
	Button hatchMode      = new JoystickButton(controlBoard, 18);
	Button cargoMode      = new JoystickButton(controlBoard, 19);
	
	public void setUpTriggers() {
		elevatorHigh.whenPressed(new ElevatorPreset(PresetHeight.kHigh));
		elevatorMedium.whenPressed(new ElevatorPreset(PresetHeight.kMedium));
		elevatorLow.whenPressed(new ElevatorPreset(PresetHeight.kLow));
		elevatorIntake.whenPressed(new ElevatorPreset(PresetHeight.kLoading));
		elevatorGround.whenPressed(new ElevatorPreset(PresetHeight.kZero));
		
		autoIntake.whenPressed(new ConditionalAutoIntake());
		//autoScore.whenPressed(new //TODO: Figure this mess out...
		
		disableVacuum.whenPressed(new InstantCommand(Robot.manipulator, () -> {
				Robot.manipulator.openSuctionValve(); 
				Robot.manipulator.setVacuum(0);
			}));

		enableVacuum.whenPressed(new InstantCommand(Robot.manipulator, () -> {
			Robot.manipulator.closeSuctionValve(); 
			Robot.manipulator.setVacuum(1);
		}));

		retractFourBar.whenPressed(new InstantCommand(Robot.manipulator, () -> Robot.manipulator.actuateFloor(Value.kReverse)));
		extendFourBar.whenPressed(new InstantCommand(Robot.manipulator, () -> Robot.manipulator.actuateFloor(Value.kForward)));
		
		leftTarget.whenPressed(new InstantCommand(() -> Limelight.switchPipeline(Pipeline.kLeftTarget)));
		leftTarget.whenReleased(new InstantCommand(() -> Limelight.switchPipeline(Pipeline.kRightTarget)));
		//enableClimber .whenPressed(new
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
	
	public double getIntakeThrottle() {
		if(controlBoard.getIntakeOut()) {
			return 1;
		} else if(controlBoard.getIntakeIn()) {
			return -1;
		} else {
			return 0;
		}	}
	
	public double getElevatorThrottle() {
		if(controlBoard.getFineAdjustUp()) {
			return 1;
		} else if(controlBoard.getFineAdjustDown()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	public double getClimberThrottle() {
		return 0;
	}
	
	public double getWheelThrottle() {
		return 0;
	}
	
	public boolean getHatchMode() {
		return controlBoard.getHatchMode();
	}

	public boolean getCargoMode() {
		return controlBoard.getCargoMode();
	}
}
