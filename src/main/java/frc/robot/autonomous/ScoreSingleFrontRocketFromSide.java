/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.AutoIntakeHatch;
import frc.robot.commands.AutoScoreHatch;
import frc.robot.commands.DriveFeet;
import frc.robot.commands.ElevatorPreset;
import frc.robot.commands.TurnToDegree;
import frc.robot.enums.Side;

public class ScoreSingleFrontRocketFromSide extends CommandGroup {
	
	public ScoreSingleFrontRocketFromSide(Side startingPosition) {
		double turnInversion = (startingPosition == Side.kLeft ? 1 : -1);
		// Drive off step
		addSequential(new DriveFeet(8.5));
		// Turn to target
		addSequential(new TurnToDegree(turnInversion * 63.88));
		//Drive toward rocket
		addSequential(new DriveFeet(6));
		//Align with rocket
		addSequential(new TurnToDegree(turnInversion * 28.767));
		//Score hatch
		addSequential(new AutoScoreHatch());
		//Drive back from scoring
		addSequential(new DriveFeet(-1));
		//TUrn to loading station
		addSequential(new TurnToDegree(turnInversion * 177.278));
		//Drive to loading station
		addSequential(new DriveFeet(12));
		//Turn towards loading station
		addSequential(new TurnToDegree(turnInversion * 180));
		//Intake hatch
		addSequential(new AutoIntakeHatch());
		//Drive back from loading statio
		addSequential(new DriveFeet(-1));
		//Turn to rocket
		addSequential(new TurnToDegree(turnInversion * 0));  
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.oi.getCancelAuton() || super.isFinished();
	}
}
