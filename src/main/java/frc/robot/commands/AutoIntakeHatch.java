package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.manipulator.IntakeHatch;
import frc.robot.commands.manipulator.PrepIntakeHatch;
import frc.robot.enums.TargetType;

public class AutoIntakeHatch extends CommandGroup {

	public AutoIntakeHatch() {
		addParallel(new PrepIntakeHatch());
		addSequential(new ApproachTarget(TargetType.kLowTarget));
		addSequential(new IntakeHatch());
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.oi.getCancelAuton() || super.isFinished();
	}
}
