/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.ElevatorPreset.PresetHeight;
import frc.robot.enums.ScoringPosition;
import frc.robot.enums.TargetType;

public class AutoScore extends CommandGroup {
	/**
	* Add your docs here.
	*/
	public AutoScore(ScoringPosition scoringPosition) {
		switch(scoringPosition) {
			case kHatchShip:
			case kHatchLow:
				addSequential(new ElevatorPreset(PresetHeight.kHatchLow));
				break;

			case kHatchMedium:
				addSequential(new ElevatorPreset(PresetHeight.kHatchMedium));
				break;

			case kHatchHigh:
				addSequential(new ElevatorPreset(PresetHeight.kHatchHigh));
				break;

			case kCargoShip:
				addSequential(new ElevatorPreset(PresetHeight.kCargoShip));
				break;

			case kCargoLow:
				addSequential(new ElevatorPreset(PresetHeight.kCargoLow));
				break;

			case kCargoMedium:
				addSequential(new ElevatorPreset(PresetHeight.kCargoMedium));
				break;

			case kCargoHigh:
				addSequential(new ElevatorPreset(PresetHeight.kCargoHigh));
				break;
		}
		
		switch(scoringPosition) {
			case kHatchShip:
			case kHatchLow:
			case kHatchMedium:
			case kHatchHigh:
				addSequential(new ApproachTarget(TargetType.kLowTarget));
				//addParallel(prepScoreHatch());
				//addSequential(scoreHatch());
				break;
			
			case kCargoShip:
				addSequential(new ApproachTarget(TargetType.kLowTarget));
				//addParallel(prepScoreCargo());
				//addSequential(scoreCargo());
				break;

			case kCargoLow:
			case kCargoMedium:
			case kCargoHigh:
				//addParallel(prepScoreCargo());
				//addSequential(scoreCargo());
				addSequential(new ApproachTarget(TargetType.kHighTarget));
				break;
		}
	}
}
