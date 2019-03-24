package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Ensures the manipulator is in the position to score a cargo.
 */

public class PrepScoreHatch extends InstantCommand {
	
	public PrepScoreHatch() {
		super();
		requires(Robot.hatchManipulator);
	}
	
	// Called once when the command executes
	@Override
	protected void initialize() {
	
	}	
}
