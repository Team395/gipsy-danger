package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;
import frc.robot.commands.ElevatorPreset.PresetHeight;
import frc.robot.commands.manipulator.IntakeCargo;
import frc.robot.enums.TargetType;
import frc.robot.utils.limelight.Limelight;

public class AutoIntakeCargo extends CommandGroup {
	
	public AutoIntakeCargo() {
		setInterruptible(false);
		addSequential(new ElevatorPreset(PresetHeight.kHatchLevelOne));
		addSequential(new ApproachTarget(TargetType.kLowTarget));
		addSequential(new ConditionalCommand(
			new IntakeCargo()) {
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
