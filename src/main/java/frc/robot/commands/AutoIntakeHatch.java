package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.commands.manipulator.IntakeHatch;
import frc.robot.commands.manipulator.PrepIntakeHatch;
import frc.robot.enums.TargetType;

public class AutoIntakeHatch extends CommandGroup {

	public AutoIntakeHatch() {
		addParallel(new PrepIntakeHatch());
		addSequential(new ApproachTarget(TargetType.kLowTarget));
		addSequential(new TimedDrive(0.25, 0.65));

		addSequential(new IntakeHatch());
		addSequential(new WaitCommand(0.25));
		addSequential(new TimedDrive(-0.25, 0.5));
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.oi.getCancelAuton() || super.isFinished();
	}
}
