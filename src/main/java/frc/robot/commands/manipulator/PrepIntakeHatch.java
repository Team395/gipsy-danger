package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

/**
 * Prepares the intake to take in a hatch.
 */
public class PrepIntakeHatch extends Command {
	final static double waitTime = 0.25;

	Timer timer = new Timer();
	boolean requiresWait = true;

	public PrepIntakeHatch() {
		requires(Robot.hatchManipulator);
	}
	
	// Called once when the command executes
	@Override
	protected void initialize() {
		if(Robot.hatchManipulator.getHatchMechanismDeployed()) {
			Robot.hatchManipulator.setHatchMechanismOpen(true);
			requiresWait = false;
		} else {
			Robot.hatchManipulator.setHatchMechanismDeployed(true);
			timer.reset();
			timer.start();
		}
	}	
	
	@Override
	protected void execute() {
		if(requiresWait && timer.hasPeriodPassed(waitTime)) {
			Robot.hatchManipulator.setHatchMechanismOpen(true);
		}
	}

	@Override
	protected boolean isFinished() {
		return Robot.hatchManipulator.getHatchMechanismOpen();
	}
}
