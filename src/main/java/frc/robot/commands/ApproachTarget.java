package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.enums.TargetType;
import frc.robot.utils.limelight.Limelight;

public class ApproachTarget extends CommandGroup {
	
	public ApproachTarget(TargetType targetType) {
		this(targetType, true);
	}

	public ApproachTarget(TargetType targetType, boolean useOffset) {
		addSequential(new InstantCommand(
				() -> {
					//if(Robot.oi.getLeftTarget()) {
					Limelight.switchPipeline(0);
					//} else {
				//		Limelight.switchPipeline(Pipeline.kRightTarget);
					//}
				}
			)
		);
		addSequential(new WaitCommand(0.25));

		addSequential(new AimAtOffset(targetType, useOffset));
		addSequential(new DriveToTarget(targetType, useOffset));
		addSequential(new InstantCommand(
				() -> {
					Limelight.switchPipeline(2);
				}
			)
		);
	}
	
}
