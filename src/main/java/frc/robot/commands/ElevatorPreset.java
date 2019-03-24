/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;
import frc.robot.enums.IntakeMode;

public class ElevatorPreset extends Command {
	
	public static enum Height {
		kCargoHigh(83.5),
		kCargoMedium(54),
		kCargoLow(27.5),
		kCargoShip(54), //TODO
		kCargoLoading(8), //TODO
		kHatchHigh(75.0),
		kHatchMedium(54.0),
		kHatchLow(19.0),
		kHatchShip(54.0),
		kHatchLoading(19.0),
		kZero(1.0),
		kMaxHeight(84.43);
		
		private final double height;
		
		public double getHeight() {
			return height;
		}
		
		private Height(double height) {
			this.height = height;
		}
	}
	
	public static enum PresetHeight {
		kHigh,
		kMedium,
		kLow,
		kShip,
		kLoading,
		kZero,
		kMaxHeight;
	}
	
	Height setpoint = Height.kZero;
	
	public ElevatorPreset(Height setpoint) {
		this.setpoint = setpoint;
	}
	
 	public ElevatorPreset(PresetHeight setpoint) {
		requires(Robot.elevator);
		if(Robot.manipulator.getIntakeMode() == IntakeMode.kHatchPanel) {
			switch(setpoint) {
				case kHigh:
					this.setpoint = Height.kHatchHigh;
					break;
				case kMedium:
					this.setpoint = Height.kHatchMedium;
					break;
				case kLow:
					this.setpoint = Height.kHatchLow;
					break;
				case kShip:
					this.setpoint = Height.kHatchShip;
					break;
				case kLoading:
					this.setpoint = Height.kHatchLoading;
					break;
				case kZero:
					this.setpoint = Height.kZero;
					break;
				case kMaxHeight:
					this.setpoint = Height.kMaxHeight;
					break;
				default:
					this.setpoint = Height.kZero;
			}
		} else if(Robot.manipulator.getIntakeMode() == IntakeMode.kCargo) {
			switch(setpoint) {
				case kHigh:
					this.setpoint = Height.kCargoHigh;
					break;
				case kMedium:
					this.setpoint = Height.kCargoMedium;
					break;
				case kLow:
					this.setpoint = Height.kCargoLow;
					break;
				case kShip:
					this.setpoint = Height.kCargoShip;
					break;
				case kLoading:
					this.setpoint = Height.kCargoLoading;
					break;
				case kZero:
					this.setpoint = Height.kZero;
					break;
				case kMaxHeight:
					this.setpoint = Height.kHatchHigh;
					break;
				default:
					this.setpoint = Height.kZero;
			}
		}
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

		Robot.elevator.setEndEffectorHeight(setpoint.getHeight());

		Scheduler.getInstance().add(
			new InstantCommand(
				Robot.manipulator,
				() -> Robot.manipulator.actuateFloor(DoubleSolenoid.Value.kForward)
			)
		);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Robot.elevator.onTarget();
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end() {
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
