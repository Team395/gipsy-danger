package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.utils.limelight.Limelight;


/**
* Ejects the hatch at the end of a scoring sequence.
*/
public class EjectHatch extends InstantCommand {
	
	public EjectHatch() {
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.hatchManipulator.setHatchMechanismOpen(true);
	}
	
}
