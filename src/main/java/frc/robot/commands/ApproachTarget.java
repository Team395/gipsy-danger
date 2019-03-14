package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.enums.TargetType;
import frc.robot.utils.limelight.Limelight;
import frc.robot.utils.limelight.Limelight.Pipeline;
import frc.robot.Robot;

public class ApproachTarget extends CommandGroup {
	
	public ApproachTarget(TargetType targetType) {
		addSequential(new InstantCommand(
				() -> {
					if(Robot.oi.controlBoard.getLeftTarget()) {
						Limelight.switchPipeline(Pipeline.kLeftTarget);
					} else {
						Limelight.switchPipeline(Pipeline.kRightTarget);
					}
				}
			)
		);

		addSequential(new AimAtOffset(targetType));
		addSequential(new DriveToTarget(targetType));
		addSequential(new InstantCommand(
				() -> Limelight.switchPipeline(Pipeline.kDriverControl)
			)
		);
	}
	
}
