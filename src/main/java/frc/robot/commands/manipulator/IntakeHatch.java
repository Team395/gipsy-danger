package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
* Crawls forward while attempting to intake the hatch. Will check the vacuum pump current draw to end early.
*/

public class IntakeHatch extends Command {
	
	
	public IntakeHatch() {
		
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end() {
		
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
