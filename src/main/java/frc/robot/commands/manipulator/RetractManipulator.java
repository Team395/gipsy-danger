package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

/**
* Retracts the manipulator while on defense.
*/
public class RetractManipulator extends Command {
	final static double closeWaitTime = 0.1;
	final static double retractWaitTime = 0.1;

	Timer timer = new Timer();

	public 
	RetractManipulator()	{
		requires(Robot.hatchManipulator);
	}
	
	// Called once when the command executes
	@Override
	protected void initialize() {
		if(Robot.hatchManipulator.getHatchMechanismOpen()) {
			Robot.hatchManipulator.setHatchMechanismOpen(false);
		} else {
			Robot.hatchManipulator.setHatchMechanismOpen(true);
		}
		timer.reset();
		timer.start();
	}	
	
	@Override
	protected void execute() {
		if(Robot.hatchManipulator.getHatchMechanismOpen() && timer.hasPeriodPassed(closeWaitTime)) {
			Robot.hatchManipulator.setHatchMechanismOpen(false);
			timer.reset();
			timer.start();
		} else if(!Robot.hatchManipulator.getHatchMechanismOpen() && timer.hasPeriodPassed(retractWaitTime)) {
			Robot.hatchManipulator.setHatchMechanismDeployed(false);
		}
	}

	@Override
	protected boolean isFinished() {
		return !Robot.hatchManipulator.getHatchMechanismDeployed();
	}

}
