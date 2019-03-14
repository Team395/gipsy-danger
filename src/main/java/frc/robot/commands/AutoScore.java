/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.ElevatorPreset.Height;
import frc.robot.commands.manipulator.EjectCargo;
import frc.robot.commands.manipulator.EjectHatch;
import frc.robot.commands.manipulator.PrepScoreCargo;
import frc.robot.commands.manipulator.PrepScoreHatch;
import frc.robot.enums.ScoringPosition;
import frc.robot.enums.TargetType;
import frc.robot.Robot;

public class AutoScore extends CommandGroup {
	/**
	* Initiates a scoring sequence for both game pieces.
	*/
	public AutoScore(ScoringPosition scoringPosition) {
		addSequential(new ElevatorPreset(Height.kCargoLow));
		
		switch(scoringPosition) {
			case kHatchShip:
			case kHatchLow:
			case kHatchMedium:
			case kHatchHigh:
				addSequential(new ApproachTarget(TargetType.kLowTarget));
				addParallel(new PrepScoreHatch());
				break;
			
			case kCargoShip:
				addSequential(new ApproachTarget(TargetType.kLowTarget));
				addParallel(new PrepScoreCargo());
				break;

			case kCargoLow:
			case kCargoMedium:
			case kCargoHigh:
				addSequential(new ApproachTarget(TargetType.kHighTarget));
				addParallel(new PrepScoreCargo());
				break;
		}

		switch(scoringPosition) {
			case kHatchShip:
			case kHatchLow:
				addSequential(new ElevatorPreset(Height.kHatchLow));
				break;

			case kHatchMedium:
				addSequential(new ElevatorPreset(Height.kHatchMedium));
				break;

			case kHatchHigh:
				addSequential(new ElevatorPreset(Height.kHatchHigh));
				break;

			case kCargoShip:
				addSequential(new ElevatorPreset(Height.kCargoShip));
				break;

			case kCargoLow:
				addSequential(new ElevatorPreset(Height.kCargoLow));
				break;

			case kCargoMedium:
				addSequential(new ElevatorPreset(Height.kCargoMedium));
				break;

			case kCargoHigh:
				addSequential(new ElevatorPreset(Height.kCargoHigh));
				break;
		}

		switch(scoringPosition) {
			case kHatchShip:
			case kHatchLow:
			case kHatchMedium:
			case kHatchHigh:
				addSequential(new EjectHatch());
				break;
			
			case kCargoShip:
			case kCargoLow:
			case kCargoMedium:
			case kCargoHigh:
				addSequential(new EjectCargo());
				break;
		}

	}

	@Override
	protected boolean isFinished() {
		return Robot.oi.getCancelAuton() || super.isFinished();
	}
}
