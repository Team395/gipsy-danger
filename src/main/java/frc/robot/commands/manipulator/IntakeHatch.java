package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
* Crawls forward while attempting to intake the hatch. Will check the vacuum pump current draw to end early.
*/

public class IntakeHatch extends InstantCommand {
	
	public IntakeHatch() {
		super();
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.hatchManipulator.setHatchSolenoid(Value.kReverse);
	}
}
