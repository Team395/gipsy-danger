/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.manipulator.EjectHatch;
import frc.robot.commands.manipulator.PrepScoreHatch;
import frc.robot.enums.TargetType;

public class AutoScoreHatch extends CommandGroup {
	/**
	 * Initiates a scoring sequence for both game pieces.
	 */
	public AutoScoreHatch() {
		addSequential(new ApproachTarget(TargetType.kLowTarget));
		addParallel(new PrepScoreHatch());
		addSequential(new EjectHatch());
	}

	@Override
	protected boolean isFinished() {
		return Robot.oi.getCancelAuton() || super.isFinished();
	}
}
