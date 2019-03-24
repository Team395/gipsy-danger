package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorPreset extends Command {

	PresetHeight setpoint;

	public static enum PresetHeight {
		kHigh(83.5),
		kMedium(54),
		kLow(27.5),
		kShip(54),
		kLoading(8),
		kZero(0),
		kMaxHeight(84.43);
		
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
