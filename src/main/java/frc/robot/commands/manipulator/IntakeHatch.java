package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class IntakeHatch extends InstantCommand {
	
	public IntakeHatch() {
		super(Robot.hatchManipulator);
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.hatchManipulator.setHatchMechanismOpen(false);
	}
}
