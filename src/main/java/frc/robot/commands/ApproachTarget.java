package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.enums.TargetType;
import frc.robot.utils.limelight.Limelight;

public class ApproachTarget extends CommandGroup {
	
	//TODO: Fix
	public ApproachTarget(TargetType targetType) {
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

		addSequential(new AimAtOffset(targetType));
		addSequential(new DriveToTarget(targetType));
		addSequential(new InstantCommand(
				() -> {
					Limelight.switchPipeline(2);
				}
			)
		);
	}
	
}
