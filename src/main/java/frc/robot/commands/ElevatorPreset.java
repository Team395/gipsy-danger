package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorPreset extends Command {

	PresetHeight setpoint;

	public static enum PresetHeight {
		kCargoLevelTwo(30),
		kCargoShip(25),
		kCargoIntake(20),
		kHatchLevelTwo(15),
		kHatchLevelOne(4),
		kZero(0),
		kMaxHeight(0);
		
		private final double height;
		
		public double getHeight() {
			return height;
		}
		
		private PresetHeight(double height) {
			this.height = height;
		}
	}
	
 	public ElevatorPreset(PresetHeight setpoint) {
		requires(Robot.elevator);
		this.setpoint = setpoint;
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.elevator.setEndEffectorHeight(setpoint.getHeight());
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
