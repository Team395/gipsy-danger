package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.enums.TargetType;

public class AutoIntakeCargo extends CommandGroup {
	public AutoIntakeCargo() {
		// addSequential(new ElevatorPreset(ElevatorPreset.Height.kHatchLoading));
		// addParallel(new PrepIntakeCargo());
		// addSequential(new ApproachTarget(TargetType.kLowTarget));
		// addSequential(new IntakeCargo());
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.oi.getCancelAuton() || super.isFinished();
	}
}
