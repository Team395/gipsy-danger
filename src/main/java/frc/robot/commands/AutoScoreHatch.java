/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;
import frc.robot.commands.manipulator.EjectHatch;
import frc.robot.commands.manipulator.IntakeCargo;
import frc.robot.enums.TargetType;
import frc.robot.utils.limelight.Limelight;

public class AutoScoreHatch extends CommandGroup {
	/**
	 * Initiates a scoring sequence for both game pieces.
	 */
	public AutoScoreHatch() {
		setInterruptible(false);
		//addSequential(new ElevatorPreset(PresetHeight.kHatchLevelOne)); //TODO: Respect button being pressed mid drive
		addSequential(new ApproachTarget(TargetType.kLowTarget));
		addSequential(new ConditionalCommand(
			new EjectHatch()) {
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
