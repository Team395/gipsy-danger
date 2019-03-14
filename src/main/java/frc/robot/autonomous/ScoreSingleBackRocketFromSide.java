/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.AutoIntakeHatch;
import frc.robot.commands.AutoScore;
import frc.robot.commands.DriveFeet;
import frc.robot.commands.ElevatorPreset;
import frc.robot.commands.ElevatorPreset.Height;
import frc.robot.commands.TurnToDegree;
import frc.robot.enums.ScoringPosition;
import frc.robot.enums.Side;
import frc.robot.Robot;

public class ScoreSingleBackRocketFromSide extends CommandGroup {
  public ScoreSingleBackRocketFromSide(Side startingPosition) {
    double turningInversion = (startingPosition == Side.kLeft ? 1 : -1);
    //Drive off the step
    addSequential(new DriveFeet(11));
    //Turn towards the rocket
    addSequential(new TurnToDegree(turningInversion * 25.059));
    //Drive to rocket
    addSequential(new DriveFeet(13.584));
    //Align with rocket
    addSequential(new TurnToDegree(151.233));
    //Score hatch on level 3
    addSequential(new AutoScore(ScoringPosition.kHatchHigh));
    //Drive back from scoring
    addSequential(new DriveFeet(-3.667));
    //Lower elevator
    addParallel(new ElevatorPreset(Height.kHatchLoading));
    //Turn to loading station
    addSequential(new TurnToDegree(turningInversion * 180));
    //Drive past rocket
    addSequential(new DriveFeet(10));
    //Turn in toward loading station
    addSequential(new TurnToDegree(turningInversion * 170.241));
    //Drive to loading statiion
    addSequential(new DriveFeet(10.933));
    //Align with loading station
    addSequential(new TurnToDegree(turningInversion * 180));
    //Load hatch
    addSequential(new AutoIntakeHatch());
    //Drive back from loading station
    addSequential(new DriveFeet(-1));
    //Turn towards field
    addSequential(new TurnToDegree(turningInversion * 0));
  }

  @Override
  protected boolean isFinished() {
    return Robot.oi.getCancelAuton() || super.isFinished();
  }
}
