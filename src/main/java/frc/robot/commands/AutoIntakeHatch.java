package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;
import frc.robot.commands.ElevatorPreset.PresetHeight;
import frc.robot.commands.manipulator.IntakeHatch;
import frc.robot.commands.manipulator.PrepIntakeHatch;
import frc.robot.enums.TargetType;
import frc.robot.utils.limelight.Limelight;

public class AutoIntakeHatch extends CommandGroup {

	public AutoIntakeHatch() {
		setInterruptible(false);
		addSequential(new ElevatorPreset(PresetHeight.kHatchLevelOne));
		addParallel(new PrepIntakeHatch());
		addSequential(new ApproachTarget(TargetType.kLowTarget));
		addSequential(new TimedDrive(0.25, 0.5));
		addSequential(new ConditionalCommand(
			new IntakeHatch()) {
				@Override
				public boolean condition() {
					return Limelight.getLastContourPulled() != null &&
						Limelight.getLastContourPulled().percentArea > Limelight.successfulDriveArea;
				}
			}
		);
	}

	@Override
	protected boolean isFinished() {
		return Robot.oi.getCancelAuton() || super.isFinished();
	}

	@Override
	protected void initialize() {
		super.initialize();
		Limelight.clearLastSeenContour();
	}

	@Override
	protected void end() {
		super.end();
		Limelight.clearLastSeenContour();
	}
}
